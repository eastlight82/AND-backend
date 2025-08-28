package com.and_backend.users.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface JwtService {
    String extractUsername(String token);          // email 또는 username 뽑기
    boolean isTokenValid(String token, UserDetails userDetails);
}
