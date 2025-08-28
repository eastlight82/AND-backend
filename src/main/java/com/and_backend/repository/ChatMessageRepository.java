package com.and_backend.repository;
import com.and_backend.home.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom_ChatRoomIdOrderByCreatedAtAsc(Long chatRoomId);
}