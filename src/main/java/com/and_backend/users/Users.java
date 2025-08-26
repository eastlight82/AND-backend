package com.and_backend.users;

import jakarta.persistence.*;
import lombok.*;

@Entity //JPA
@Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder//Lombok
public class Users {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long usersId;

    private String email;
    private String password;
    private String name;
    private Integer age;
    private String gender;
}