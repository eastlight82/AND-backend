package com.and_backend.home.qna.dto;

import com.and_backend.home.qna.QnaQ;

public record QuestionResponse(
        Long id,
        Long lossCaseId,
        Long qnaQBankId
) {
    public static QuestionResponse from(QnaQ q) {
        return new QuestionResponse(
                q.getQnaQId(),
                q.getLossCase().getLossCaseId(),
                q.getQnaQBank().getQnaQBankId()
        );
    }
}