package com.and_backend.preTest.dto;

import com.and_backend.preTest.PreTestQ;

import java.util.List;

public record PreTestQuestionRes(Long questionId, String text, Integer score, List<PreTestOptionRes> options) {
    public static PreTestQuestionRes from(PreTestQ q) {
        var opts = q.getOptions().stream().map(PreTestOptionRes::from).toList();
        return new PreTestQuestionRes(q.getPreTestQId(), q.getText(), q.getScore(), opts);
    }
}