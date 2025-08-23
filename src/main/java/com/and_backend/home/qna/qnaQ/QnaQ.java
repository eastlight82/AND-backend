package com.and_backend.home.qna.qnaQ;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "qnaQ")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaQ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private LossCase lossCase;
}
