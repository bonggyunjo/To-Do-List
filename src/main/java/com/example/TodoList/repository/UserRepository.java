package com.example.TodoList.repository;



import com.example.TodoList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Optional로 반환한 이유는 로그인 검증에서 orElseThrow 예외처리를 위해 Optional를 사용
    Optional<User> findByUserId(String userId);

    User findByNickname(String nickname);

    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}
