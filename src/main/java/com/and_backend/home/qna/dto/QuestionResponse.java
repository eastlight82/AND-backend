package com.and_backend.home.qna.dto;

import com.and_backend.home.qna.QnaQ;

public record QuestionResponse(Long id, String text
) {
    public static QuestionResponse from(QnaQ q) {
        return new QuestionResponse(q.getQnaQId(), q.getQnaQBank().getText());
    }
}