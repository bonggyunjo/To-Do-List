package com.example.TodoList.service;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.Bookmark;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.BookmarkRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Board board = boardRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!bookmarkRepository.existsByUser_UserIdAndBoard_PostId(userId, postId)) {
            Bookmark bookmark = Bookmark.builder()
                    .user(user)
                    .board(board)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    public void removeFavorite(String userId, Long postId) {
        bookmarkRepository.findByUser_UserIdAndBoard_PostId(userId, postId).ifPresent(bookmarkRepository::delete);
    }

    public List<Bookmark> getFavorites(String userId) {
        return bookmarkRepository.findByUser_UserId(userId);
    }
}
