package com.example.TodoList.controller;

import com.example.TodoList.dto.BookmarkDto;
import com.example.TodoList.entity.Bookmark;
import com.example.TodoList.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;

    @PostMapping("/bookmark/post/{userId}/{postId}")
    public ResponseEntity<Void> addFavorite(@RequestBody BookmarkDto bookmarkDto) {
        bookmarkService.addFavorite(bookmarkDto.getUserId(), bookmarkDto.getPostId());
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/bookmark/delete/{userId}/{postId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable String userId, @PathVariable Long postId) {
        bookmarkService.removeFavorite(userId, postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookmark/get/{userId}")
    public ResponseEntity<List<Bookmark>> getFavorites(@PathVariable String userId) {
        List<Bookmark> bookmarks = bookmarkService.getFavorites(userId);
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/bookmark/exists/{userId}/{postId}")
    public ResponseEntity<Boolean> checkFavorite(@PathVariable String userId, @PathVariable Long postId) {
        boolean exists = bookmarkService.isFavorite(userId, postId);
        return ResponseEntity.ok(exists);
    }
}
