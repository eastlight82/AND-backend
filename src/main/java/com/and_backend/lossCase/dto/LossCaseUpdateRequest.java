package com.and_backend.lossCase.dto;

import com.and_backend.enums.*;

import java.time.LocalDate;

public record LossCaseUpdateRequest(
        LossSubject subject,
        WithTime withTime,
        LocalDate lossDate,
        LossReason lossReason,
        CopeWay copeWay,
        LossSubjectFamily lossSubjectFamily,
        String photoBase64) {
}
