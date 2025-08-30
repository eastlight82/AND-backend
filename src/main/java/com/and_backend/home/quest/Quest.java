package com.and_backend.home.quest;

import com.and_backend.enums.*;
import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "\"quest\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Quest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_bank_id", nullable = false)
    private QuestBank questBank;

    private String text;
    @Enumerated(EnumType.STRING)
    private LossSubject subject;
    @Enumerated(EnumType.STRING)
    private CopeWay copeway;

    @Column
    private boolean completed= false;

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loss_case_id", nullable = false)
    private LossCase lossCase;
}
