package com.example.TodoList.dto;

import com.example.TodoList.entity.record.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long parentId;
    private List<Long> tagIds;
    private String userId;
    private Integer priority; // 우선순위 필드 추가
    private ProgressStatus progressStatus; // 추가

}
