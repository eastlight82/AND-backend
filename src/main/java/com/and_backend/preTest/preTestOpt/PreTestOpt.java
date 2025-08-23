package com.and_backend.preTest.preTestOpt;

import com.and_backend.preTest.preTestQ.PreTestQ;
import jakarta.persistence.*;

@Entity @Table(name = "preTestOpt")
public class PreTestOpt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private PreTestQ preTestQ;
}
