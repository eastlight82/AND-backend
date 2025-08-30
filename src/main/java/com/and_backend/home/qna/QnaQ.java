package com.and_backend.home.qna;

import com.and_backend.enums.LossSubject;
import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "qnaQ")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaQ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QnaQId;

    private String text;
    @Enumerated(EnumType.STRING)
    private LossSubject subject;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "qnaqBankId", nullable = false)
    private QnaQBank qnaQBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lossCaseId", nullable = false)
    private LossCase lossCase;
}
