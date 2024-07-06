package com.example.TodoList.repository;



import com.example.TodoList.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> finbyuserId(String userId);

    Optional<User> finbynickname(String nickname);
}
