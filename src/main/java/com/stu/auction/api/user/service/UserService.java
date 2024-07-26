package com.stu.auction.api.user.service;

import com.stu.auction.api.user.entity.User;
import com.stu.auction.api.user.repository.UserRepository;
import com.stu.auction.core.common.response.ErrorResponseDto;
import com.stu.auction.core.common.response.SuccessResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 생성자 주입
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> saveUser (User user){

        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(new SuccessResponseDto("회원가입성공" ,user , 200 ));
        }catch(Exception e ) {
            log.error("회원가입중 에러발생 " , e  );
            return ResponseEntity.badRequest().body( new ErrorResponseDto( "회원가입중 에러가 발생했습니다. " , e.getMessage() ) );
        }




    }
}
