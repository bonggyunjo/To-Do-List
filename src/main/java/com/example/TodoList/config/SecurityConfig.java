package com.example.TodoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        //특정한 경로에 요청이 왔을 때 실행 ,
        http
                .authorizeHttpRequests((auth) ->auth // 동작 순서는 위에서부터 아래로, 3.1이상 부터는 람다 표현식으로
                       // .requestMatchers("/signup","/login","/").permitAll() //모든 사용자가 접근 가능
                       // .requestMatchers("/admin").hasRole("ADMIN")
                       // .anyRequest().authenticated() // 위의 경로 외에는 모든 사용자가 접근 하지 못하게


                        .anyRequest().permitAll() // 임시로 모든 경로가 접근 가능하도록 해둠
                );

        return http.build();
    }
}
