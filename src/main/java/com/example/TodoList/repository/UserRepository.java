package com.example.TodoList.repository;



import com.example.TodoList.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);

    User findByNickname(String nickname);

    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}
