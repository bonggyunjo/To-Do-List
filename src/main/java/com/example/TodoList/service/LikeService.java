package com.example.TodoList.service;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.UserLike;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.LikeRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public void addLike(String userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Board board = boardRepository.findById(postId).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        if (!likeRepository.existsByUser_UserIdAndBoard_PostId(userId, postId)) {
            UserLike like = new UserLike();
            like.setUser(user);
            like.setBoard(board);
            like.setLike(1);
            likeRepository.save(like);

            board.incrementLikeCount();
            boardRepository.save(board);
        } else {
            throw new RuntimeException("이미 추가하였습니다.");
        }
    }

    public void removeLike(String userId, Long postId) {
        UserLike like = likeRepository.findByUser_UserIdAndBoard_PostId(userId, postId)
                .orElseThrow(() -> new RuntimeException("제거할 수 없습니다."));

        Board board = like.getBoard();
        likeRepository.delete(like);
        board.decrementLikeCount();
        boardRepository.save(board);
    }

    public List<UserLike> getLikes(String userId) {
        return likeRepository.findByUser_UserId(userId);
    }

    public boolean isLike(String userId, Long postId) {
        return likeRepository.existsByUser_UserIdAndBoard_PostId(userId, postId);
    }

}
