package com.and_backend.home.qna.qnaA;

import com.and_backend.home.qna.qnaQ.QnaQ;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "qnaA")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private QnaQ qnaQ;
}
