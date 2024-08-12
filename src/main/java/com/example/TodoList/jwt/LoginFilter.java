package com.example.TodoList.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userId = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(userId);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password, null);

        // 검증을 받음, authenticationManager가 검증을 담당
        return authenticationManager.authenticate(authToken);
    }

    // 사용자 ID를 요청 본문에서 가져오는 메소드
    // 여기서 obtainUsername(request) 메소드는 기본적으로 폼 데이터에서 사용자 이름을 추출하는데, 클라이언트가 JSON 형식으로 데이터를 보내는 경우, 이 메소드는 올바른 값을 반환
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String userId = null;
        try {
            // 요청 본문에서 JSON 데이터 읽기
            String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
            userId = jsonMap.get("userId"); // JSON에서 userId 가져오기
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userId;
    }

    // 로그인 성공 시 실행하는 메서드 (여기서 JWT 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        // JWT 발급 로직
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        // 인증 실패 처리 로직
    }
}
