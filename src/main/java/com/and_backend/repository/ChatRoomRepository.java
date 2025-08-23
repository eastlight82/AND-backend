package com.and_backend.repository;

import com.and_backend.home.chat.chatRoom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
