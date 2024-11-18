package com.example.TodoList.repository;

import com.example.TodoList.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_UserId(String userId);

    boolean existsByUser_UserIdAndBoard_PostId(String userId, Long postId);
}
