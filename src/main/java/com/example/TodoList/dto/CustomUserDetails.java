package com.example.TodoList.dto;

import com.example.TodoList.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUserId(); // User 클래스에서 userId를 반환하는 메소드
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // User 클래스에서 password를 반환하는 메소드
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole())); // User 클래스에서 role을 반환하는 메소드
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되지 않았는지 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠겨있는지 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정이 활성화여부
    }
}
