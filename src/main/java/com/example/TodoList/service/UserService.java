package com.example.TodoList.service;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.entity.user.User;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //회원가입
    public void registerUser(SignUpDto signUpDto) {
        String userId = signUpDto.getUserId();
        String password = signUpDto.getPassword();

        Boolean isExist = userRepository.existsByUserId(userId);
        if (isExist) {

            return;

        }

        if (userRepository.findByUserId(signUpDto.getUserId()) != null) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다");
        }
        if (userRepository.findByNickname(signUpDto.getNickname()) != null) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }

        User user = new User();

        user.setUserId(userId);
        user.setPassword(bCryptPasswordEncoder.encode(password)); //비밀번호 인코딩
        user.setRole("ROLE_ADMIN"); // 어드민 권한 설정

        userRepository.save(user);
    }
    //로그인
    public User UserLogin(String userId) {
        return userRepository.findByUserId(userId);
    }

}

