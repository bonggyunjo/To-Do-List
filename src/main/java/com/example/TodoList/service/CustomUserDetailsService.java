package com.example.TodoList.service;

import com.example.TodoList.dto.CustomUserDetails;
import com.example.TodoList.entity.user.User;
import com.example.TodoList.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User userData = userRepository.findByUserId(userId);
        if(userData!=null){

            return new CustomUserDetails((org.apache.catalina.User) userData);
        }
        return null;
    }
}
