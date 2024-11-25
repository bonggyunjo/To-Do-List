package com.example.TodoList.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
    private String userId;
    private Long postId;
}
