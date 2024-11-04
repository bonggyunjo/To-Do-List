package com.example.TodoList.repository;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.record.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long > {
    List<Page> findAll();
}
