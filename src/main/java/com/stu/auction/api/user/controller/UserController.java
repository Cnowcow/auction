package com.stu.auction.api.user.controller;

import com.stu.auction.api.user.entity.User;
import com.stu.auction.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        return userService.saveUser(user);
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//
//        return ResponseEntity.ok("성공");
//    }

}
