package com.example.TodoList.service;

import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.BlockRepository;
import com.example.TodoList.repository.PageRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return blockRepository.save(block); // 블록을 저장
    }
}
