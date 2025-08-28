package com.and_backend.users;

import com.and_backend.repository.UsersRepository;
import com.and_backend.users.dto.*;
import com.and_backend.users.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository users;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponse signup(SignupRequest req) {
        log.info("signup start email={}", req.email());

        if (users.existsByEmail(req.email())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        Users u = Users.builder()
                .email(req.email())
                .password(encoder.encode(req.password()))
                .name(req.name())
                .age(req.age())
                .gender(req.gender())
                .build();
        Users saved = users.save(u);
        log.info("signup ok id={}", saved.getUsersId());
        return new UserResponse(u.getUsersId(), u.getEmail(), u.getName(), u.getAge(), u.getGender());
    }

    public AuthResponse login(LoginRequest req) {
        var u = users.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        if (!encoder.matches(req.password(), u.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        String token = jwtUtil.generate(u.getEmail(), Map.of("uid", u.getUsersId()));

        return new AuthResponse(token, "Bearer",
                new UserResponse(u.getUsersId(), u.getEmail(), u.getName(), u.getAge(), u.getGender()));
    }
}
