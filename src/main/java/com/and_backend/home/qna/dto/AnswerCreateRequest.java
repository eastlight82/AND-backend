package com.and_backend.home.qna.dto;

import jakarta.validation.constraints.NotBlank;

// 답변 생성 요청
public record AnswerCreateRequest(
        @NotBlank String text
) {}

