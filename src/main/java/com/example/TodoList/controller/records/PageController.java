package com.example.TodoList.controller.records;


import com.example.TodoList.dto.PageDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.PageRepository;
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

    @Autowired
    private PageRepository pageRepository;
    @PostMapping("/pages/create")
    public ResponseEntity<Page> createPage(@RequestBody PageDto pagedto) {
        String userId = pagedto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Page newPage = pageService.createPage(pagedto.getTitle(), pagedto.getContent(), pagedto.getUserId());
        return ResponseEntity.ok(newPage);
    }

    @GetMapping("/pages/{userId}")
    public ResponseEntity<List<Page>> getPagesByUserId(@PathVariable String userId, @RequestParam(required = false) Boolean deleted) {
        List<Page> pages;
        if (deleted != null) {
            pages = pageRepository.findByUser_UserIdAndDeleted(userId, deleted);
        } else {
            pages = pageRepository.findByUser_UserId(userId); // 기본적으로 모든 페이지 반환
        }
        return ResponseEntity.ok(pages);
    }
    @PutMapping("/pages/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatedPage) {
        Page updatepage = pageService.updatePage(id, updatedPage.getTitle(), updatedPage.getContent());
        return ResponseEntity.ok(updatepage);
    }


    @PatchMapping("/pages/{id}/delete")
    public ResponseEntity<Page> moveToTrash(@PathVariable Long id) {
        Page page = pageService.moveToTrash(id);
        return  ResponseEntity.ok(page);
    }


    @GetMapping("/pages/deleted")
    public ResponseEntity<List<Page>> getDeletedPages(){
        List<Page> deletedPages = pageService.getDeletedPage();
        return ResponseEntity.ok(deletedPages);
    }

    @PatchMapping("/pages/{id}/restore")
    public ResponseEntity<Page> restorePage(@PathVariable Long id) {
        Page restoredPage = pageService.restorePage(id);
        return ResponseEntity.ok(restoredPage);
    }

    @DeleteMapping("/pages/{id}/permanent")
    public ResponseEntity<Void> permanentDeletePage(@PathVariable Long id) {
        pageService.permanentDeletePage(id);
        return ResponseEntity.noContent().build();
    }
}

