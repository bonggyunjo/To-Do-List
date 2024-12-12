package com.example.TodoList.service;

import com.example.TodoList.dto.CommentDto;
import com.example.TodoList.entity.Comment;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.CommentRepository;
import com.example.TodoList.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {

        if (!boardRepository.existsById(comment.getBoard().getPostId())) {
            throw new EntityNotFoundException("Board not found");
        }


        if (comment.getUser() == null || comment.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }


        User user = userRepository.findById(comment.getUser().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        comment.setUser(user);
        return commentRepository.save(comment);
    }



    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByBoard_PostId(postId);
        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .nickname(comment.getNickname())
                        .userId(comment.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new EntityNotFoundException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }
}