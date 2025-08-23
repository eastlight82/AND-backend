package com.and_backend.users;

import jakarta.persistence.*;
import lombok.*;

@Entity //JPA
@Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder//Lombok
public class Users {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String pw;
    private String name;
    private Integer age;
    private String gender;
}