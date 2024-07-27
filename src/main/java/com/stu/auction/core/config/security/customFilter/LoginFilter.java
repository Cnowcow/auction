package com.stu.auction.core.config.security.customFilter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stu.auction.core.config.security.jwt.JWTUtil;
import com.stu.auction.core.config.security.user.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /** 인증 시도 한 값*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        String username = obtainUsername(request);
//        String password = obtainPassword(request);
//
        // BODY  {"username":"ljy2","password":"ljy"} 방식으로 변경
        Gson gson = new Gson();
        String username = null;
        String password = null;

        try (BufferedReader reader = request.getReader()) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            username = jsonObject.get("username").getAsString();
            password = jsonObject.get("password").getAsString();
        } catch (IOException e) {
            throw new RuntimeException("Request body parsing error", e);
        }

        //FIXME :: 권한 자체를 REQUEST 를 통해 요청 시도 하는부분이 생길시  authories 부분 추가할예정 .


        System.out.println(" 인증을 시도 한 user name  test ::: :::::" + username);
        System.out.println("인증을 시도한 passwrod   test :::::: " + password);

        //  CustomAuthenticationProvider ( AuthenticationProvider 로 인증을 위임한다 .)
        UsernamePasswordAuthenticationToken authToken =UsernamePasswordAuthenticationToken.unauthenticated( username , password) ;
        return authenticationManager.authenticate(authToken);   //
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//        String role = auth.getAuthority();


        String token = jwtUtil.createJwt(username
                                        , authorities.stream().map(GrantedAuthority::getAuthority ).toList()
                                        , jwtUtil.getAccessExprationTime() );

        response.addHeader("Authorization", "Bearer " + token);

        // 응답 본문 작성
        Gson gson = new Gson();
        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("message", "로그인 성공했습니다.");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(responseBody));


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
