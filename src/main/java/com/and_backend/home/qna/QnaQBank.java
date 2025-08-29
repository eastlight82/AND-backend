package com.and_backend.home.qna;

import com.and_backend.enums.LossSubject;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="qnaQ_bank")
@Getter @Setter @NoArgsConstructor
public class QnaQBank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaQBankId;

    private String text;

    @Enumerated(EnumType.STRING)
    private LossSubject subject;

    @OneToMany(mappedBy = "qnaQBank")
    private List<QnaQ> qnaqs= new ArrayList<>();
}
