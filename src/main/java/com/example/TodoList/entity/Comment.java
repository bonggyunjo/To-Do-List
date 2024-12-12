package com.example.TodoList.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Comment(Long commentId, Board board, User user, String content, LocalDateTime createdAt) {
        this.commentId=commentId;
        this.board =board;
        this.user= user;
        this.content=content;
        this.createdAt=createdAt;
    }

    public String getNickname() {
        return user != null ? user.getNickname() : null;
    }
    public String getUserId() {
        return user != null ? user.getUserId() : null;
    }

}