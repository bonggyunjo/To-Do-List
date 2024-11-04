package com.example.TodoList.controller;

import com.example.TodoList.dto.BlockDto;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BlockController {
    @Autowired
    private BlockService blockService;

    @PostMapping("/pages/block/create")
    public ResponseEntity<Block> createBlock(@RequestBody BlockDto blockDto) {
        Block newBlock = blockService.createBlock(blockDto.getType(), blockDto.getTitle(), blockDto.getContent(), blockDto.getId(), blockDto.getUserId());
        return ResponseEntity.ok(newBlock);
    }
}
