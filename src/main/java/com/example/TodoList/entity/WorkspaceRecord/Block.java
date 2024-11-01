package com.example.TodoList.entity.WorkspaceRecord;

import com.example.TodoList.entity.User;
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
    private String type;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @Column(nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
