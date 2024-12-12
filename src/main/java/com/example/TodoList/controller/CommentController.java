package com.example.TodoList.controller;

import com.example.TodoList.dto.CommentDto;
import com.example.TodoList.entity.Comment;
import com.example.TodoList.repository.CommentRepository;
import com.example.TodoList.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 추가
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.addComment(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdComment);
    }

    @GetMapping("/board/{postId}")
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}