package com.and_backend.home.chat;

import com.and_backend.lossCase.LossCase;
import com.and_backend.lossCase.LossCaseService;
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
    public ChatRoom createRoom(@RequestParam Long lossCaseId) {
        LossCase lc = lossCaseService.get(lossCaseId);
        return chatService.createRoom(lc);
    }

    @PostMapping("/rooms/{roomId}/messages")
    public ChatMessage updateMsg(@PathVariable Long roomId, @RequestBody SendReq req) {
        return chatService.updateMsg(roomId, req.sender, req.text);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessage> list(@PathVariable Long roomId) {
        return chatService.list(roomId);
    }

    @PostMapping("/rooms/{roomId}/close")
    public ChatRoom close(@PathVariable Long roomId) { return chatService.close(roomId); }

    @Data
    static class SendReq { String sender; String text; }
}
