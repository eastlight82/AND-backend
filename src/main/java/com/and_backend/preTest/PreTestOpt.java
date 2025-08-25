package com.and_backend.preTest;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "preTestOpt")
@Data
public class PreTestOpt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preTestOptId;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preTestQId", nullable = false)
    private PreTestQ preTestQ;
}
