package com.and_backend.repository;
import com.and_backend.home.chat.chatMessage.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {}