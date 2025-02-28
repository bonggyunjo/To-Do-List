package com.example.TodoList.repository;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAll();

    List<Board> findByUser(User user);
}
