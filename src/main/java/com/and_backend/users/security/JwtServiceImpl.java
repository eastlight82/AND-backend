package com.and_backend.users.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtServiceImpl implements JwtService {

    // application.yml(or properties)에 설정한 Base64 인코딩된 시크릿
    @Value("${jwt.secret}")
    private String secretBase64;

    private Key getSignKey() {
        // HS256용 256-bit 이상 키 (Base64)
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretBase64));
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 토큰 subject에 username/email을 넣었다는 가정
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username != null && username.equals(userDetails.getUsername());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
