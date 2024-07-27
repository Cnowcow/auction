package com.stu.auction.core.config.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@Getter
public class JWTUtil {

    private SecretKey secretKey;
    private final long accessExprationTime ;
    private final long refreshExpirationTime;

    private final String USERNAME = "username";
    private final String ROLE = "role";
//    private final String USERNAME = "username";


    public JWTUtil(@Value("${spring.jwt.secret-key}")String secret
                   ,@Value("${spring.jwt.access-expiration-time}")String accessExprationTime
                    ,@Value("${spring.jwt.refresh-expiration-time}")String refreshExpirationTime
    ) {

        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessExprationTime = Long.parseLong(accessExprationTime);
        this.refreshExpirationTime = Long.parseLong(refreshExpirationTime) ;
    }

    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(USERNAME, String.class);
    }

    public ArrayList<String> getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(ROLE, ArrayList.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }


    public String createJwt(String username, List<String> role, Long expiredMs) {

        return Jwts.builder()
                .claim(USERNAME, username)
                .claim(ROLE, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
