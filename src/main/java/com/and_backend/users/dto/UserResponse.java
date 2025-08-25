package com.and_backend.users.dto;

public record UserResponse(
        Long userId, String email, String name, Integer age, String gender
) {}
