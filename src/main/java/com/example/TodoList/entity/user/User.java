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

    // 닉네임 = unique key
    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    // 한줄소개
    @Column(nullable = false)
    private String intro;

    private String role;
}
