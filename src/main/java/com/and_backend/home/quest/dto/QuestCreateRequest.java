package com.and_backend.home.quest.dto;

import com.and_backend.home.quest.QuestBank;

public record QuestCreateRequest(Long lossCaseId, String subject, String copeWay) {}
