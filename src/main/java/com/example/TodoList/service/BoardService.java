package com.example.TodoList.service;

import com.example.TodoList.dto.BoardDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.BookmarkRepository;
import com.example.TodoList.repository.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private LikeRepository likeRepository;
    @Transactional
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> BoardDto.builder()
                        .postId(board.getPostId())
                        .userId(board.getUser().getUserId())
                        .nickname(board.getUser().getNickname())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
    public Board createBoard(Board board) {
        if (board.getTitle() == null || board.getContent() == null) {
            throw new RuntimeException("제목과 내용은 필수입니다.");
        }
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
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }
    public Board deleteBoard(Long postId) {
        Optional<Board> optionalBoard = boardRepository.findById(postId);
        if (optionalBoard.isPresent()) {
            boardRepository.delete(optionalBoard.get());
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
        return null;
    }

    public BoardDto getBoardPostId(Long postId) {
        Optional<Board> boardOptional = boardRepository.findById(postId);
        return boardOptional.map(this::convertToDto).orElse(null);
    }
    private BoardDto convertToDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setPostId(board.getPostId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setNickname(board.getUser().getNickname());
        dto.setCreatedAt(board.getCreatedAt());
        return dto;
    }

    public int getTotalBookmarks(Long postId){
        return bookmarkRepository.countByBoard_PostId(postId);
    }

    public int getTotalLikes(Long postId){
        return likeRepository.countByBoard_PostId(postId);
    }
}
