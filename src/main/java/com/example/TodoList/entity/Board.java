package com.example.TodoList.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

        @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    private String content;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    private int BookmarkCount;

    public void incrementFavoriteCount() {
        this.BookmarkCount++;
    }

    // 즐겨찾기 수를 감소시키는 메서드
    public void decrementFavoriteCount() {
        this.BookmarkCount--;
    }
    @Builder
    public Board(Long postId, User user, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt,int bookmarkCount) {
        this.postId = postId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.BookmarkCount= bookmarkCount;
    }
}
