package com.example.TodoList.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name = "userId", nullable = false)
    private String userId;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String intro;

    private String role;


    //회원 정보 수정
    public void updateDetails(String password, String nickname, String intro) {
        this.password = password;
        this.nickname = nickname;
        this.intro = intro;
    }
}
