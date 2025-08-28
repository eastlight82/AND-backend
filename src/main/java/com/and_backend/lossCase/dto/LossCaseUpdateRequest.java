package com.and_backend.lossCase.dto;

import com.and_backend.enums.CopeWay;
import com.and_backend.enums.LossReason;
import com.and_backend.enums.LossSubject;
import com.and_backend.enums.WithTime;

import java.time.LocalDate;

public record LossCaseUpdateRequest(
        LossSubject subject,
        WithTime withTime,
        LocalDate lossDate,
        LossReason lossReason,
        CopeWay copeWay,
        String photoBase64) {
}
