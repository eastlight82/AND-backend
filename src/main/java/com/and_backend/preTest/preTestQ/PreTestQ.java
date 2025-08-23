package com.and_backend.preTest.preTestQ;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "preTestQ")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PreTestQ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String text;
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY) //lazy loading
    @JoinColumn(name = "id", nullable = false)
    private LossCase lossCase;
}