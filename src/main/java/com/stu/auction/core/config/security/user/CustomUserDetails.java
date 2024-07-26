package com.stu.auction.core.config.security.user;

import com.stu.auction.api.user.entity.User;
import com.stu.auction.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 스프링 시큐리티 userDtails 구현체
 * */
public class CustomUserDetails implements UserDetails {
    User user ;

    public CustomUserDetails(User user ) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //FIXME :: 추후 정책에 따라 수정
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }
    //FIXME :: 추후 정책에 따라 수정
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }
    //FIXME :: 추후 정책에 따라 수정
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }
    //FIXME :: 추후 정책에 따라 수정
    @Override
    public boolean isEnabled() {

        return true;
    }
}
