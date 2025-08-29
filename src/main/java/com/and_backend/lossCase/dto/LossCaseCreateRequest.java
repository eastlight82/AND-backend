package com.and_backend.lossCase.dto;

import com.and_backend.enums.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LossCaseCreateRequest(
        @NotNull LossSubject subject,
        @NotNull WithTime withTime,
        @NotNull LocalDate lossDate,
        @NotNull LossReason lossReason,
        @NotNull CopeWay copeWay,
        LossSubjectFamily lossSubjectFamily,
        String photoBase64 // null 허용
) {}
