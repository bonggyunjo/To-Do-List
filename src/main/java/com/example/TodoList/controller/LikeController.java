package com.example.TodoList.controller;

import com.example.TodoList.dto.LikeDto;
import com.example.TodoList.entity.Bookmark;
import com.example.TodoList.entity.UserLike;
import com.example.TodoList.service.BookmarkService;
import com.example.TodoList.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping("/like/post/{userId}/{postId}")
    public ResponseEntity<Void> addLike(@RequestBody LikeDto likeDto){
        likeService.addLike(likeDto.getUserId(), likeDto.getPostId());
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/like/delete/{userId}/{postId}")
    public ResponseEntity<Void> removeLike(@PathVariable String userId,@PathVariable Long postId){
        likeService.removeLike(userId,postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/like/get/{userId}")
    public ResponseEntity<List<UserLike>> getFavorites(@PathVariable String userId) {
        List<UserLike> like = likeService.getLikes(userId);
        return ResponseEntity.ok(like);
    }

    @GetMapping("/like/exists/{userId}/{postId}")
    public ResponseEntity<Boolean> checkFavorite(@PathVariable String userId, @PathVariable Long postId) {
        boolean exists = likeService.isLike(userId, postId);
        return ResponseEntity.ok(exists);
    }
}