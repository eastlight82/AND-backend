package com.and_backend.home.chat.dto;

import com.and_backend.home.chat.ChatMessage;

import java.time.LocalDateTime;

public record ChatMessageResponse(
        Long id,
        Long chatRoomId,
        String sender,
        String text,
        LocalDateTime createdAt
) {
    public static ChatMessageResponse from(ChatMessage m) {
        return new ChatMessageResponse(
                m.getId(),
                m.getChatRoom() != null ? m.getChatRoom().getChatRoomId() : null,
                m.getSender(),
                m.getText(),
                m.getCreatedAt()
        );
    }
}
