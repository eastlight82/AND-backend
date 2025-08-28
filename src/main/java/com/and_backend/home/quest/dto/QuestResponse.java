package com.and_backend.home.quest.dto;

import com.and_backend.home.quest.Quest;

public record QuestResponse(Long questId, Long lossCaseId, String text) {
    public static QuestResponse from(Quest q) {
        return new QuestResponse(q.getQuestId(), q.getLossCase().getLossCaseId(), q.getText());
    }
}