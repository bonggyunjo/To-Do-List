package com.example.TodoList.entity.record;

import com.example.TodoList.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String title;

    @Column(nullable = false)
    private String type;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT") // MEDIUMTEXT로 명시적으로 설정
    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @Column(name="block_order", nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
