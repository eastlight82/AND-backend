package com.and_backend.preTest;

import com.and_backend.lossCase.LossCase;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "preTestQ")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = {"lossCase", "options"}) //순환 참조 예방
public class PreTestQ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long preTestQId;
    private String text;
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY) //lazy loading
    @JoinColumn(name = "lossCaseId", nullable = false)
    private LossCase lossCase;

    @OneToMany(mappedBy = "preTestQ", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PreTestOpt> options = new ArrayList<>();
}