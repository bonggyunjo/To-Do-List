package com.example.TodoList.controller;

import com.example.TodoList.dto.BoardDto;
import com.example.TodoList.dto.PageDto;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}

