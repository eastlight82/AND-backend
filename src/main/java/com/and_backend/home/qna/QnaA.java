package com.and_backend.home.qna;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "qnaA")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qnaQId", nullable = false)
    private QnaQ qnaQ;
}
