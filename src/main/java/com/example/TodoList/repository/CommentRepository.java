package com.example.TodoList.repository;

import com.example.TodoList.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findByBoard_PostId(Long postId);
}
