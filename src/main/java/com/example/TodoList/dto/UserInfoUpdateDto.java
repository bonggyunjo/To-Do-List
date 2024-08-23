package com.example.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUpdateDto {
    private String userId;
    private String password;
    private String nickname;
    private String intro;
}
