package com.and_backend.home.chat.dto;

import jakarta.validation.constraints.NotNull;

public record ChatRoomCreateRequest(
        @NotNull Long lossCaseId
) {}
