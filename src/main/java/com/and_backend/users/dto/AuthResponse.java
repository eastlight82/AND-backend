package com.and_backend.users.dto;

public record AuthResponse(
        String accessToken, String tokenType, UserResponse user
) {}
