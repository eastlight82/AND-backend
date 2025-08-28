package com.and_backend.home.qna.dto;

import com.and_backend.home.qna.QnaQ;

public record QuestionResponse(
        Long id,
        String text,
        Long lossCaseId
) {
    public static QuestionResponse from(QnaQ q) {
        return new QuestionResponse(
                q.getQnaQId(),
                q.getText(),
                q.getLossCase().getLossCaseId()
        );
    }
}