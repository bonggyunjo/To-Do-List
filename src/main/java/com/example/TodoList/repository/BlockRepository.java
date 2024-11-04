package com.example.TodoList.repository;

import com.example.TodoList.entity.record.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block,Long> {
    List<Block> findByPageId(Long pageId);
}
