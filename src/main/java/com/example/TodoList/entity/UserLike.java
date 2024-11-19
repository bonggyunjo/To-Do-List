package com.example.TodoList.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name="likes")
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId", nullable = false)
    private Board board;


    @Column(name="likeCount")
    private Integer like;

    public UserLike(Long id, User user, Board board, Integer like) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.like = (like!=null)?null:0;
    }
}
