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

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //회원가입
    public void registerUser(SignUpDto signUpDto) {
        String userId = signUpDto.getUserId();
        String password = signUpDto.getPassword();
        String nickname = signUpDto.getNickname();
        String intro = signUpDto.getIntro();


        Optional<User> existingUser = userRepository.findByUserId(signUpDto.getUserId());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다");
        }

        if (userRepository.findByNickname(signUpDto.getNickname()) != null) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }

        User user = new User();

        user.setUserId(userId);
        user.setPassword(bCryptPasswordEncoder.encode(password)); //비밀번호 인코딩
        user.setNickname(nickname);
        user.setIntro(intro);
        user.setRole("ROLE_ADMIN"); // 어드민 권한 설정

        userRepository.save(user);
    }
    //회원 정보 수정
    public void updateUser(SignUpDto signUpDto) {
        String userId = signUpDto.getUserId();
        Optional<User> existingUser = userRepository.findByUserId(userId);

        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("해당 아이디를 가진 사용자가 존재하지 않습니다.");
        }

        User user = existingUser.get();

        boolean isUpdated = false;

        if (signUpDto.getPassword() != null && !signUpDto.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword())); // 비밀번호 인코딩
            isUpdated = true;
        }
        if (signUpDto.getNickname() != null && !signUpDto.getNickname().equals(user.getNickname())) {
            user.setNickname(signUpDto.getNickname());
            isUpdated = true;
        }
        if (signUpDto.getIntro() != null && !signUpDto.getIntro().equals(user.getIntro())) {
            user.setIntro(signUpDto.getIntro());
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new IllegalArgumentException("회원 정보를 수정할 수 없습니다.");
        }

    }
}
