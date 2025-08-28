package com.and_backend.home.chat.dto;

import com.and_backend.home.chat.ChatRoom;

import java.time.LocalDateTime;

public record ChatRoomResponse(
        Long chatRoomId,
        Long lossCaseId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ChatRoomResponse from(ChatRoom r) {
        return new ChatRoomResponse(
                r.getChatRoomId(),
                r.getLossCase() != null ? r.getLossCase().getLossCaseId() : null,
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }
}
