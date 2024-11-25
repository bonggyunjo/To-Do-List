package com.example.TodoList.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", nullable = false)
    private Board board;


    private int BookmarkCount;

    public Bookmark(Long id, User user, Board board, int BookmarkCount) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.BookmarkCount = BookmarkCount;
    }
}
