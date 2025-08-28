package com.and_backend.lossCase.dto;

import com.and_backend.enums.CopeWay;
import com.and_backend.enums.LossReason;
import com.and_backend.enums.LossSubject;
import com.and_backend.enums.WithTime;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LossCaseCreateRequest(
        @NotNull LossSubject subject,
        @NotNull WithTime withTime,
        @NotNull LocalDate lossDate,
        @NotNull LossReason lossReason,
        @NotNull CopeWay copeWay,
        String photoBase64 // null 허용
) {}
