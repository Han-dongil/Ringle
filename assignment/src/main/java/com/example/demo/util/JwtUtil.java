package com.example.demo.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "jT0m8Fj2Jz5qX1r6dG9zV2B+YpR3uM5qL9kX7eN2P1o=\n"; // 32자 이상으로 설정
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 토큰 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //유저id 가져오기
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  
                .build()
                .parseClaimsJws(token)
                .getBody(); 
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getToken(HttpServletRequest request) {
    	if(null!=request.getHeader("Authorization") &&request.getHeader("Authorization").startsWith("Bearer ")) 
    		return request.getHeader("Authorization").substring(7);
    	else
    		return null;
    }

    // JWT 토큰에서 'role'을 추출하는 메서드
    public String getRole(HttpServletRequest request) {
        return extractClaims( getToken(request) ).get("role", String.class);  // role을 클레임에서 추출
    }
}
