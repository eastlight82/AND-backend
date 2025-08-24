package com.and_backend.home.quest;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "\"quest\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Quest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lossCaseId", nullable = false)
    private LossCase lossCase;
}
