package com.and_backend.preTest.dto;

import java.util.List;

public record PreTestListResponse(Long lossCaseId, List<PreTestQuestionRes> questions) {}