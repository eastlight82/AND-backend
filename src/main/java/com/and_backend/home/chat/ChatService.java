package com.and_backend.home.chat;

import com.and_backend.home.chat.dto.ChatMessageCreateRequest;
import com.and_backend.home.chat.dto.ChatMessageResponse;
import com.and_backend.home.chat.dto.ChatRoomCreateRequest;
import com.and_backend.home.chat.dto.ChatRoomResponse;
import com.and_backend.lossCase.LossCase;
import com.and_backend.repository.ChatMessageRepository;
import com.and_backend.repository.ChatRoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository roomRepo;
    private final ChatMessageRepository msgRepo;
    private final EntityManager em;

    @Transactional
    public ChatRoomResponse createRoom(ChatRoomCreateRequest req) {
        // LossCase 프록시 참조 (불필요한 select 방지)
        var lossCaseRef = em.getReference(LossCase.class, req.lossCaseId());

        var r = ChatRoom.builder()
                .lossCase(lossCaseRef)
                .createdAt(LocalDateTime.now())
                .build();

        return ChatRoomResponse.from(roomRepo.save(r));
    }

    @Transactional
    public ChatMessageResponse addMessage(Long roomId, ChatMessageCreateRequest req) {
        // 존재 확인 후 프록시 획득
        if (!roomRepo.existsById(roomId)) {
            throw new EntityNotFoundException("ChatRoom %d not found".formatted(roomId));
        }
        var roomRef = em.getReference(ChatRoom.class, roomId);

        var m = ChatMessage.builder()
                .chatRoom(roomRef)
                .sender(req.sender())
                .text(req.text())
                .createdAt(LocalDateTime.now())
                .build();

        // 방의 updatedAt 갱신
        roomRef.setUpdatedAt(LocalDateTime.now());

        return ChatMessageResponse.from(msgRepo.save(m));
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponse> list(Long roomId) {
        if (!roomRepo.existsById(roomId)) {
            throw new EntityNotFoundException("ChatRoom %d not found".formatted(roomId));
        }
        return msgRepo.findByChatRoom_ChatRoomIdOrderByCreatedAtAsc(roomId)
                .stream().map(ChatMessageResponse::from).toList();
    }

    @Transactional
    public ChatRoomResponse close(Long roomId) {
        var r = roomRepo.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("ChatRoom %d not found".formatted(roomId)));
        r.setUpdatedAt(LocalDateTime.now());
        return ChatRoomResponse.from(roomRepo.save(r));
    }
}