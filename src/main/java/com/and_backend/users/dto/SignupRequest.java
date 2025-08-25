package com.and_backend.users.dto;

import jakarta.validation.constraints.*;

public record SignupRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 64) String password,
        @NotBlank String name,
        Integer age,
        String gender
) {}