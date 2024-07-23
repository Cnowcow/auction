package com.stu.auction.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/*")
                .permitAll()
//                .antMatchers("/", "/home").permitAll() // 특정 경로 접근 허용
                .anyRequest().authenticated() ;// 나머지 경로는 인증 필요
//                .and()
//                .formLogin()
//                .loginPage("/custom-login") // 사용자 정의 로그인 페이지 설정
//                .permitAll() // 로그인 페이지 접근 허용
//                .and()
//                .logout()
//                .permitAll(); // 로그아웃 접근 허용

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**"); // 정적 리소스는 보안 무시
    }
}