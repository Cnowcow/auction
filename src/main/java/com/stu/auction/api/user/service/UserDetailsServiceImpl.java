package com.stu.auction.api.user.service;

import com.stu.auction.api.user.entity.User;
import com.stu.auction.api.user.repository.UserRepository;
import com.stu.auction.core.config.security.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * spring security 에서 제공해주는 로그인 구현체 사용
 * */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        // 스프링에서 제공해주는 User 객체
        return new CustomUserDetails(user);
    }
}
