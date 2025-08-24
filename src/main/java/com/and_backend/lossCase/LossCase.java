package com.and_backend.lossCase;

import com.and_backend.enums.CopeWay;
import com.and_backend.enums.LossReason;
import com.and_backend.enums.LossSubject;
import com.and_backend.enums.WithTime;
import com.and_backend.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Table(name = "lossCase")
@Getter
@Setter
public class LossCase {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // 예시: enum 등은 String 컬럼이라고 가정
    private LossSubject subject;
    private WithTime withTime;
    private LocalDate lossDate;
    private LossReason lossReason;
    private CopeWay copeWay;

    @Lob
    @Column(name="photo")
    private byte[] photo;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UsersId", insertable=false, updatable=false)
    private Users users;
}
