package com.example.TodoList.service;

import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.PageRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PageService {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    public Page createPage(String title, String content, String userId){
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Page page = Page.builder()
                .title(title)
                .content(content)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .user(user)
                .build();
        return pageRepository.save(page);
    }

}
