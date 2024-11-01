package com.example.TodoList.entity.WorkspaceRecord;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // 블록 타입 (예: TEXT, IMAGE 등)

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @Column(nullable = false)
    private int order; // 페이지 내에서의 순서
}
