package com.stu.auction.api.user.enums;

import lombok.Getter;

/*
* 회원 권환 관리를하는 enum 클래스 .
*  name() 으로 권한 값 사용하면됨  . desc 문자그대로 description 용도
* */
@Getter
public enum UserRole {

    ROLE_GUEST("게스트"),
    ROLE_USER("회원"),
    ROLE_SUPERUSER("슈퍼회원"),
    ROLE_ADMIN("관리자"),
    ROLE_SUPERADMIN("슈퍼관리자")

    ;

    private String desc;

    UserRole(String desc){
        this.desc = desc;
    }
}