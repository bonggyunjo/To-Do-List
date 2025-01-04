package com.example.TodoList.repository;

import com.example.TodoList.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_UserId(String userId);
    

    Optional<Bookmark> findByUser_UserIdAndBoard_PostId(String userId, Long postId);

    void deleteByBoard_PostId(Long postId); // 추가
    boolean existsByUser_UserIdAndBoard_PostId(String userId, Long postId);

    @Query("SELECT COUNT(b) FROM Bookmark b WHERE b.board.postId = :postId")
    int countByBoard_PostId(Long postId);
}
