package com.example.TodoList.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlockDto {
    private String type;
    private String content;
    private Long id;
    private String userId;
}
