package com.example.TodoList.controller;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.dto.UserInfoUpdateDto;
import com.example.TodoList.repository.UserRepository;
import com.example.TodoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
public class  UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
        try {
            userService.registerUser(signUpDto);
            return new ResponseEntity<>("회원가입에 성공하였습니다.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //닉네임 중복확인
    @GetMapping("/checknickname/{nickname}")
    public ResponseEntity<Boolean> checkNickname(@PathVariable(value="nickname") String nickname) {
        boolean exists = userRepository.existsByNickname(nickname);
        boolean isAvailable = !exists;
        return ResponseEntity.ok(isAvailable);
    }

    //아이디 중복확인
    @GetMapping("/checkuserId/{userId}")
    public ResponseEntity<Boolean> checkuserId(@PathVariable(value="userId") String userId) {
        boolean exists = userRepository.existsByUserId(userId);
        boolean isAvailable = !exists;
        return ResponseEntity.ok(isAvailable);
    }

    //회원 정보 수정
    @PutMapping("/mypage/update")
    public ResponseEntity<String> updateUser(@RequestBody UserInfoUpdateDto userInfoUpdateDto) {

        try {
            String userId = userInfoUpdateDto.getUserId(); // DTO에서 userId 추출
            userService.updateUser(userId, userInfoUpdateDto);
            return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 탈퇴
    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId){
        userService.deleteuser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료");
    }
}