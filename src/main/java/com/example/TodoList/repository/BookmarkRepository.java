package com.example.TodoList.repository;

import com.example.TodoList.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_UserId(String userId);
    

    Optional<Bookmark> findByUser_UserIdAndBoard_PostId(String userId, Long postId);

    boolean existsByUser_UserIdAndBoard_PostId(String userId, Long postId);
}
