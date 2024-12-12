package com.example.TodoList.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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

    @Column(name = "like_count")
    private Integer like;

    public void incrementFavoriteCount() {
        this.BookmarkCount++;
    }

    public void decrementFavoriteCount() {
        this.BookmarkCount--;
    }
    public void incrementLikeCount() {
        if (this.like == null) {
            this.like = 0;
        }
        this.like++;
    }

    public void decrementLikeCount() {
        if (this.like != null && this.like > 0) {
            this.like--;
        }
    }

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // 댓글 추가 메서드
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    @Builder
    public Board(Long postId, User user, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt,int bookmarkCount,Integer like) {
        this.postId = postId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.BookmarkCount= bookmarkCount;
        this.like = (like!=null)?null:0;
    }
}
