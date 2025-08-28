package com.and_backend.home.chat;

import com.and_backend.home.chat.dto.ChatMessageCreateRequest;
import com.and_backend.home.chat.dto.ChatMessageResponse;
import com.and_backend.home.chat.dto.ChatRoomCreateRequest;
import com.and_backend.home.chat.dto.ChatRoomResponse;
import com.and_backend.lossCase.LossCase;
import com.and_backend.lossCase.LossCaseService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat") @RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final LossCaseService lossCaseService;

    @PostMapping("/rooms")
    public ChatRoomResponse createRoom(@RequestParam Long lossCaseId, @RequestBody ChatRoomCreateRequest req) {
        return chatService.createRoom(req);
    }

    @PostMapping("/rooms/{roomId}/messages")
    public ChatMessageResponse addMessage(@PathVariable Long roomId,
                                          @Valid @RequestBody ChatMessageCreateRequest req) {
        return chatService.addMessage(roomId, req);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessageResponse> list(@PathVariable Long roomId) {
        return chatService.list(roomId);
    }

    @PostMapping("/rooms/{roomId}/close")
    public ChatRoomResponse close(@PathVariable Long roomId) { return chatService.close(roomId); }
}
