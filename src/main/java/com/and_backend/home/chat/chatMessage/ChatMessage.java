package com.and_backend.home.chat.chatMessage;

import com.and_backend.home.chat.chatRoom.ChatRoom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "\"ChatMessages\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String text;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"id\"", nullable = false)
    private ChatRoom chatRoom;
}