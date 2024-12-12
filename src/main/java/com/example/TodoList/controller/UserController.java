package com.example.TodoList.controller;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.dto.UserInfoDto;
import com.example.TodoList.dto.UserInfoUpdateDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.UserRepository;
import com.example.TodoList.service.BoardService;
import com.example.TodoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class  UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
        try {
            userService.registerUser(signUpDto);
            return new ResponseEntity<>("회원가입에 성공하였습니다.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checknickname/{nickname}")
    public ResponseEntity<Boolean> checkNickname(@PathVariable(value="nickname") String nickname) {
        boolean exists = userRepository.existsByNickname(nickname);
        boolean isAvailable = !exists;
        return ResponseEntity.ok(isAvailable);
    }


    @GetMapping("/checkuserId/{userId}")
    public ResponseEntity<Boolean> checkuserId(@PathVariable(value="userId") String userId) {
        boolean exists = userRepository.existsByUserId(userId);
        boolean isAvailable = !exists;
        return ResponseEntity.ok(isAvailable);
    }


    @PutMapping("/mypage/update")
    public ResponseEntity<String> updateUser(@RequestBody UserInfoUpdateDto userInfoUpdateDto) {

        try {
            String userId = userInfoUpdateDto.getUserId();
            userService.updateUser(userId, userInfoUpdateDto);
            return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String userId) {
        UserInfoDto userInfo = userService.getUserInfo(userId);
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId){
        userService.deleteuser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료");
    }

    @GetMapping("/mypage/board/get/{userId}")
    public ResponseEntity<List<Board>> myPage(@PathVariable String userId) {
        User user = userService.findByUsername(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<Board> posts = boardService.getBoardByUser(user);

        return ResponseEntity.ok(posts);
    }
}