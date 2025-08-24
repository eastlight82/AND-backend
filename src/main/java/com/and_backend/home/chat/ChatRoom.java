package com.and_backend.home.chat;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "\"chatRoom\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"lossCaseId\"", nullable = false)
    private LossCase lossCase;
}