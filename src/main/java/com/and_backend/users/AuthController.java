package com.and_backend.users;

import com.and_backend.users.dto.AuthResponse;
import com.and_backend.users.dto.LoginRequest;
import com.and_backend.users.dto.SignupRequest;
import com.and_backend.users.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody @Valid SignupRequest req) {
        return ResponseEntity.ok(authService.signup(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    // 토큰 확인용
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        // authentication.getName() = email
        // 실서비스라면 email로 User를 다시 조회해 최신 정보를 리턴
        return ResponseEntity.ok(new UserResponse(null, authentication.getName(), null, null, null));
    }
}
