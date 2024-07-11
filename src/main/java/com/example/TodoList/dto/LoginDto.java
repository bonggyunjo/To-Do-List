package com.example.TodoList.dto;

import lombok.*;

@Data
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String password;
}
