package com.stu.auction.api.user.entity;

import com.stu.auction.api.user.dto.RegisterUserDto;
import com.stu.auction.api.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true )
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt ;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt ;

//    private List<Auction> auctions;



    //아래는 spring security 관련
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;


    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    // Convert UserRole to GrantedAuthority
    public Set<GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }


//    public User (RegisterUserDto registerUserDto) {
//
//    }

    public User ( RegisterUserDto registerUserDto ) {
        this.username = registerUserDto.getUsername();
        this.password = registerUserDto.getPassword();
        this.email = registerUserDto.getEmail();
        this.roles = Set.of( UserRole.ROLE_USER) ;
    }



    public static User loginByToken( String username , Set<UserRole> roles ) {
        User u = new User();
        u.username = username;
        u.roles = roles;
        return u;
    }

    public void setEncodingPassword(String encodingPassword ) {
        this.password = encodingPassword ;
    }

}
