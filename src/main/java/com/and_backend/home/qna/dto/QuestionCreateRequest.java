package com.and_backend.home.qna.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionCreateRequest(@NotNull Long lossCaseId) {
}
