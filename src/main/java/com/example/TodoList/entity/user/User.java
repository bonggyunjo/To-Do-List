package com.example.TodoList.entity.user;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {

    // 아이디 = primary key
    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String intro;

    private String role;
}
