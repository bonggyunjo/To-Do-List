package com.example.TodoList.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 직렬화에서 제외
public class UserInfoUpdateDto {
    private String userId;
    private String password;
    private String nickname;
    private String intro;
}
