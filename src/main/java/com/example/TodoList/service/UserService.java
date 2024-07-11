package com.example.TodoList.service;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.entity.user.User;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //회원가입
    public User registerUser(SignUpDto signUpDto) {
        if (userRepository.findByUserId(signUpDto.getUserId())!=null) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다");
        }
        if (userRepository.findByNickname(signUpDto.getNickname())!=null) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }

        // dto -> entito
        User user = signUpDto.toEntity();

        // repository에게 entity를 디비에 저장하게 시킴
        User saved = userRepository.save(user);

        return saved;
    }

    //로그인
    public User UserLogin(String userId) {
        return userRepository.findByUserId(userId);
    }

}

