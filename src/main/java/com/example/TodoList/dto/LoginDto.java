package com.example.TodoList.dto;

import com.example.TodoList.entity.User;
import lombok.*;

@Data
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String password;
    @Builder
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
