package com.and_backend.lossCase.dto;

import com.and_backend.enums.*;
import com.and_backend.lossCase.LossCase;

import java.time.LocalDate;

public record LossCaseDto(
        Long lossCaseId,
        LossSubject subject,
        WithTime withTime,
        LocalDate lossDate,
        LossReason lossReason,
        CopeWay copeWay,
        LossSubjectFamily subjectFamily
) {
    public static LossCaseDto of(LossCase entity) {
        return new LossCaseDto(
                entity.getLossCaseId(),
                entity.getSubject(),
                entity.getWithTime(),
                entity.getLossDate(),
                entity.getLossReason(),
                entity.getCopeWay(),
                entity.getSubjectFamily()
        );
    }
}
