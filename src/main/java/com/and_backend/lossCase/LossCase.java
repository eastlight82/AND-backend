package com.and_backend.lossCase;

import com.and_backend.enums.*;
import com.and_backend.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Table(name = "lossCase")
@Getter @Setter
public class LossCase {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long lossCaseId;

    // 예시: enum 등은 String 컬럼이라고 가정
    @Enumerated(EnumType.STRING)
    private LossSubject subject;
    @Enumerated(EnumType.STRING)
    private WithTime withTime;
    private LocalDate lossDate;
    @Enumerated(EnumType.STRING)
    private LossReason lossReason;
    @Enumerated(EnumType.STRING)
    private CopeWay copeWay;
    @Enumerated(EnumType.STRING)
    private LossSubjectFamily subjectFamily;

    @Lob
    @Column(name="photo")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usersId")
    private Users users;

    protected LossCase(){}; //default constructor
    public LossCase(Long id){
        this.lossCaseId=id;
    }
}
