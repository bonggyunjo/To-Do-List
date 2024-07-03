package com.example.TodoList.repository;



import com.example.TodoList.entity.user.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user, String> {

}
