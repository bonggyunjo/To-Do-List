package com.example.TodoList.controller;

import com.example.TodoList.dto.BoardDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;


    @GetMapping("/boards")
    public ResponseEntity<List<BoardDto>> getBoards() {
        List<BoardDto> boards = boardService.getAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping("/boards/create")
    public ResponseEntity<Board> createBoards(@RequestBody Board board) {
        Board createBoard = boardService.createBoard(board);
        return ResponseEntity.ok(createBoard);
    }

    @PutMapping("/boards/{postId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long postId, @RequestBody Board updatedBoard) {
        Board updateboard = boardService.updateBoard(postId, updatedBoard.getTitle(), updatedBoard.getContent());
        return ResponseEntity.ok(updateboard);
    }

    @DeleteMapping("/boards/{postId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long postId) {
        boardService.deleteBoard(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/boards/detail/{postId}")
    public ResponseEntity<BoardDto> getDetailBoard(@PathVariable Long postId) {
        BoardDto boarddto = boardService.getBoardPostId(postId);
        if (boarddto != null) {
            return ResponseEntity.ok(boarddto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{postId}/bookmark/count")
    public ResponseEntity<Integer> getBookmarkCount(@PathVariable Long postId){
        int count = boardService.getTotalBookmarks(postId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/{postId}/like/count")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long postId){
        int count = boardService.getTotalLikes(postId);
        return ResponseEntity.ok(count);
    }
}
