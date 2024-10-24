package com.example.TodoList.entity;


import com.example.TodoList.dto.UserInfoUpdateDto;
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


    //회원 정보 수정
    public void updateDetails(String password, String nickname, String intro) {
        this.password = password; // 비밀번호 업데이트
        this.nickname = nickname; // 닉네임 업데이트
        this.intro = intro; // 소개 업데이트
    }
}
