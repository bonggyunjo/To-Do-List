package com.example.TodoList.dto;

import com.example.TodoList.entity.user.User;
import lombok.*;

@Data
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {
    private String userId;
    private String password;
    private String nickname;
    private String intro;

    @Builder
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .intro(intro)
                .build();
    }
}
