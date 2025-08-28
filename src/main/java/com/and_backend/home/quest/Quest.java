package com.and_backend.home.quest;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "\"quest\"")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Quest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questId;

    private String text;

    @Column(columnDefinition = "boolean type false")
    private boolean completed;

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lossCaseId", nullable = false)
    private LossCase lossCase;
}
