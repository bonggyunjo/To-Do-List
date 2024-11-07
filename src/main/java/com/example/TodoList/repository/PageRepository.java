package com.example.TodoList.repository;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.entity.record.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long > {
    List<Page> findAll();

    List<Page> findByUser_UserId(String userId);

    List<Page> findByDeleted(boolean deleted);

    List<Page> findByUser_UserIdAndDeleted(String userId, boolean deleted);
}
