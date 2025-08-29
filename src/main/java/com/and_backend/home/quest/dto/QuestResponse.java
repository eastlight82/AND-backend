package com.and_backend.home.quest.dto;

import com.and_backend.home.quest.Quest;
import com.and_backend.home.quest.QuestBank;

public record QuestResponse(Long questId, Long lossCaseId, Long questBankId) {
    public static QuestResponse from(Quest q) {
        return new QuestResponse(q.getQuestId(), q.getLossCase().getLossCaseId(), q.getQuestBank().getQuestBankId());
    }
}