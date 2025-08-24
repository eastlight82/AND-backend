package com.and_backend.home.chat;

import com.and_backend.lossCase.LossCase;
import com.and_backend.repository.ChatMessageRepository;
import com.and_backend.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository roomRepo;
    private final ChatMessageRepository msgRepo;

    public ChatRoom createRoom(LossCase lc) {
        ChatRoom r = ChatRoom.builder().lossCase(lc).createdAt(LocalDateTime.now()).build();
        return roomRepo.save(r);
    }

    public ChatMessage updateMsg(Long roomId, String sender, String text) {
        ChatRoom room = roomRepo.findById(roomId).orElseThrow();
        ChatMessage m = ChatMessage.builder()
                .chatRoom(room)
                .sender(sender)
                .text(text)
                .createdAt(LocalDateTime.now())
                .build();
        return msgRepo.save(m);
    }

    public List<ChatMessage> list(Long roomId) {
        ChatRoom room = roomRepo.findById(roomId).orElseThrow();
        // 단순히 전체 조회 (필요하면 정렬 메서드 추가)
        return msgRepo.findAll().stream().filter(m -> m.getChatRoom().getChatRoomId().equals(room.getChatRoomId())).toList();
    }

    public ChatRoom close(Long roomId) {
        ChatRoom r = roomRepo.findById(roomId).orElseThrow();
        r.setUpdatedAt(LocalDateTime.now());
        return roomRepo.save(r);
    }
}