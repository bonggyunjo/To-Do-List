package com.example.TodoList.service;

import com.example.TodoList.dto.SignUpDto;
import com.example.TodoList.dto.UserInfoUpdateDto;
import com.example.TodoList.entity.user.User;
import com.example.TodoList.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Transactional
    public void updateUser(String userId, UserInfoUpdateDto userInfoUpdateDto) throws IOException {
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new IOException("사용자를 찾을 수 없습니다.")) // 사용자 없을 경우 예외 처리
                .updateDetails(userInfoUpdateDto.getPassword(), userInfoUpdateDto.getNickname(), userInfoUpdateDto.getIntro());
    }

    //회원 탈퇴

    @Transactional
    public void deleteuser(String userId){
        userRepository.deleteById(userId);
    }
}
