package com.example.TodoList.controller.records;


import com.example.TodoList.dto.PageDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.UserRepository;
import com.example.TodoList.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/pages/create")
    public ResponseEntity<Page> createPage(@RequestBody PageDto pagedto) {
        String userId = pagedto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자가 없으면 예외 발생
        Page newPage = pageService.createPage(pagedto.getTitle(), pagedto.getContent(), pagedto.getUserId());
        return ResponseEntity.ok(newPage);
    }

    @GetMapping("/pages/{userId}")
    public List<Page> getPagesByUserId(@PathVariable String userId) {
        return pageService.findByUserId(userId);
    }

    @PutMapping("/pages/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatedPage) {
        Page updatepage = pageService.updatePage(id, updatedPage.getTitle(), updatedPage.getContent());
        return ResponseEntity.ok(updatepage);
    }

    @DeleteMapping("/pages/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }
}

