package com.and_backend.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity //JPA
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder //Lombok
@Table(name="users") //table명
public class UserEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // BCrypt

    private String displayName;
    private int coinBalance;

    private LocalDate breakupDate; // 기준일
}