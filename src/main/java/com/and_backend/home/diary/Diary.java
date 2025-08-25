package com.and_backend.home.diary;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "\"diary\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Diary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private byte[] symbol;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lossCaseId", nullable = false)
    private LossCase lossCase;
}
