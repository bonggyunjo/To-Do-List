package com.example.TodoList.service;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.Bookmark;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.BookmarkRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    public void addFavorite(String userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        // 로그 추가
        System.out.println("User: " + user);
        System.out.println("Board: " + board + ", postId: " + board.getPostId());

        if (bookmarkRepository.existsByUser_UserIdAndBoard_PostId(userId, postId)) {
            throw new RuntimeException("이미 추가하였습니다.");
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setBoard(board);
        bookmark.setBookmarkCount(1);
        bookmarkRepository.save(bookmark);

        board.incrementFavoriteCount();
        boardRepository.save(board);
    }


    public void removeFavorite(String userId, Long postId) {
        Bookmark bookmark = bookmarkRepository.findByUser_UserIdAndBoard_PostId(userId, postId)
                .orElseThrow(() -> new RuntimeException("제거할 수 없습니다."));

        Board board = bookmark.getBoard();
        bookmarkRepository.delete(bookmark);
        board.decrementFavoriteCount();
        boardRepository.save(board);
    }

    public List<Bookmark> getFavorites(String userId) {
        return bookmarkRepository.findByUser_UserId(userId);
    }

    public boolean isFavorite(String userId, Long postId) {
        return bookmarkRepository.existsByUser_UserIdAndBoard_PostId(userId, postId);
    }
}
