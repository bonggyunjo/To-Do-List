package com.example.TodoList.controller;


import com.example.TodoList.dto.PageDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @PostMapping("/pages/create")
    public ResponseEntity<Page> createPage(@RequestBody PageDto pagedto) {
        Page newPage = pageService.createPage(pagedto.getTitle(), pagedto.getContent(), pagedto.getUserId());
        return ResponseEntity.ok(newPage);
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Page>> getPages() {
        List<Page> pages = pageService.getAllPages();
        return new ResponseEntity<>(pages, HttpStatus.OK);
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
