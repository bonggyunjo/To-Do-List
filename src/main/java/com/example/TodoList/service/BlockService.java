package com.example.TodoList.service;

import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.BlockRepository;
import com.example.TodoList.repository.PageRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    @Autowired
    BlockRepository blockRepository;

    @Autowired
    PageRepository pageRepository;

    @Autowired
    UserRepository userRepository;

    public Block createBlock(String type, String title, String content, Long pageId, String userId) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new RuntimeException("페이지를 찾을 수 없습니다."));
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Block block = Block.builder()
                .type(type)
                .title(title)
                .content(content)
                .page(page)
                .order(0)
                .user(user)
                .build();

        page.getBlocks().add(block);
        pageRepository.save(page);

        return blockRepository.save(block);
    }

    public List<Block> getBlocksByPageId(Long pageId) {
        return blockRepository.findByPageId(pageId);
    }

    public Optional<Block> getBlocksById(Long blockId) {
        return blockRepository.findById(blockId);
    }

    public Block updateBlock(Long id, String title, String content) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("블록을 찾을 수 없습니다."));

        block.setTitle(title);
        block.setContent(content);

        return blockRepository.save(block);
    }

    public Block moveToTrash(Long id) {
        Block block = getBlocksById(id).orElseThrow(() -> new RuntimeException("error"));
        block.setDeleted(true);
        return blockRepository.save(block);
    }

    public List<Block> getDeletedBlock(){
        return blockRepository.findByDeleted(true);
    }

    public Block restoreBlock(Long id) {
        Optional<Block> optionalBlock = blockRepository.findById(id);
        if (optionalBlock.isPresent()) {
            Block block = optionalBlock.get();
            if (block.isDeleted()) {
                block.setDeleted(false);
                return blockRepository.save(block);
            } else {
                throw new RuntimeException("블록 페이지는 이미 복원되었습니다.");
            }
        } else {
            throw new RuntimeException("블록 페이지를 찾을 수 없습니다.");
        }
    }

    public void permanentDeleteBlock(Long id) {
        Optional<Block> optionalBlock = blockRepository.findById(id);
        if (optionalBlock.isPresent()) {
            blockRepository.delete(optionalBlock.get());
        } else {
            throw new RuntimeException("블록 페이지를 찾을 수 없습니다.");
        }
    }
}

