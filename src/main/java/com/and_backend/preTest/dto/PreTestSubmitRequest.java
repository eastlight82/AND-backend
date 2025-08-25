package com.and_backend.preTest.dto;

import java.util.List;

public record PreTestSubmitRequest(Long lossCaseId, List<PreTestAnswerItem> answers) {}
