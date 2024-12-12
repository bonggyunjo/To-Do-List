package com.example.TodoList.service;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.dto.UserInfoDto;
import com.example.TodoList.dto.UserInfoUpdateDto;
import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import com.example.TodoList.repository.BoardRepository;
import com.example.TodoList.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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


    public UserInfoDto getUserInfo(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUserId(user.getUserId());
            userInfoDto.setNickname(user.getNickname());
            userInfoDto.setIntro(user.getIntro());
            return userInfoDto;
        }
        return null;
    }


    @Transactional
    public void updateUser(String userId, UserInfoUpdateDto userInfoUpdateDto) throws IOException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IOException("사용자를 찾을 수 없습니다."));

        if (userInfoUpdateDto.getPassword() != null) {
            // 비밀번호 해싱
            String hashedPassword = bCryptPasswordEncoder.encode(userInfoUpdateDto.getPassword());
            user.setPassword(hashedPassword); // 해싱된 비밀번호로 업데이트
        }
        if (userInfoUpdateDto.getNickname() != null) {
            user.setNickname(userInfoUpdateDto.getNickname());
        }
        if (userInfoUpdateDto.getIntro() != null) {
            user.setIntro(userInfoUpdateDto.getIntro());
        }

        userRepository.save(user);
    }


    @Transactional
    public void deleteuser(String userId){
        userRepository.deleteById(userId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserId(username);
    }

}