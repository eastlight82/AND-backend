package com.and_backend.home.chat.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatMessageCreateRequest(
        @NotBlank String sender,
        @NotBlank String text
) {}
