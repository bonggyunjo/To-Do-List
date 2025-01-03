package com.example.TodoList.entity.record;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public enum ProgressStatus {
    PENDING,      // 대기 중
    IN_PROGRESS,  // 진행 중
    COMPLETED     // 완료
}
