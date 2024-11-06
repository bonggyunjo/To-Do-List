package com.example.TodoList.controller.records;

import com.example.TodoList.dto.BlockDto;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Block>> getBlockByPageId(@PathVariable Long pageId) {
        List<Block> blocks = blockService.getBlocksByPageId(pageId);
        return ResponseEntity.ok(blocks);
    }

    @GetMapping("/pages/block/{blockId}")
    public ResponseEntity<Optional<Block>> getBlockById(@PathVariable Long blockId) {
        Optional<Block> block = blockService.getBlocksById(blockId);
        return ResponseEntity.ok(block);
    }


    @PutMapping("pages/blocks/{id}")
    public ResponseEntity<Block> updateBlock(@PathVariable Long id, @RequestBody BlockDto blockDto) {
        Block updatedBlock = blockService.updateBlock(id, blockDto.getTitle(), blockDto.getContent());
        return ResponseEntity.ok(updatedBlock);
    }

    @DeleteMapping("/pages/blocks/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        blockService.deleteBlock(id);
        return ResponseEntity.noContent().build();
    }
}