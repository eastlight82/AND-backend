package com.and_backend.home.diary.dto;
import com.and_backend.home.diary.Diary;

import java.time.LocalDateTime;

public record DiaryResponse(Long diaryId, Long lossCaseId, String text, LocalDateTime createdAt) {
    public static DiaryResponse from(Diary d) {
        return new DiaryResponse(d.getDiaryId(), d.getLossCase().getLossCaseId(), d.getText(), d.getCreatedAt());
    }
}