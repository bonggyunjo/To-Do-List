package com.example.TodoList.controller;

import com.example.TodoList.dto.LikeDto;
import com.example.TodoList.service.BookmarkService;
import com.example.TodoList.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping("/like/post/{userId}/{postId}")
    public ResponseEntity<Void> addLike(@RequestBody LikeDto likeDto){
        likeService.addLike(likeDto.getUserId(), likeDto.getPostId());
        return ResponseEntity.status(201).build();
    }


}