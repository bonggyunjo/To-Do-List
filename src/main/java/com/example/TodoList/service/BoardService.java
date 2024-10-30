package com.example.TodoList.service;

import com.example.TodoList.dto.BoardDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> BoardDto.builder()
                        .postId(board.getPostId())
                        .userId(board.getUser().getUserId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

    public Board updateBoard(Long postId, String title, String content) {
        Optional<Board> optionalBoard = boardRepository.findById(postId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(title);
            board.setContent(content);
            return boardRepository.save(board);
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다."); // 예외 처리
        }
    }
}
