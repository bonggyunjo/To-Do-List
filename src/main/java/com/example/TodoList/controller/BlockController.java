package com.example.TodoList.controller;

import com.example.TodoList.dto.BlockDto;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BlockController {
    @Autowired
    private BlockService blockService;

    @PostMapping("/pages/block/create")
    public ResponseEntity<Block> createBlock(@RequestBody BlockDto blockDto) {
        Block newBlock = blockService.createBlock(blockDto.getType(), blockDto.getTitle(), blockDto.getContent(), blockDto.getId(), blockDto.getUserId());
        return ResponseEntity.ok(newBlock);
    }

    @GetMapping("/pages/blocks/{pageId}")
    public ResponseEntity<List<Block>> getBlockById(@PathVariable Long pageId) {
        List<Block> blocks = blockService.getBlocksByPageId(pageId);
        return ResponseEntity.ok(blocks);
    }
}
