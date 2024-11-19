package com.example.TodoList.repository;

import com.example.TodoList.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<UserLike, Long> {
    List<UserLike> findByUser_UserId(String userId);


    Optional<UserLike> findByUser_UserIdAndBoard_PostId(String userId, Long postId);

    boolean existsByUser_UserIdAndBoard_PostId(String userId, Long postId);

    @Query("SELECT COUNT(b) FROM UserLike b WHERE b.board.postId = :postId")
    int countByBoard_PostId(Long postId);
}
