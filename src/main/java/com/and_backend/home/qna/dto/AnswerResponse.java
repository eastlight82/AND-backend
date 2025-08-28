package com.and_backend.home.qna.dto;

import com.and_backend.home.qna.QnaA;

public record AnswerResponse(
        Long id,
        String text,
        Long questionId
) {
    public static AnswerResponse from(QnaA a) {
        return new AnswerResponse(
                a.getQnaAId(),
                a.getText(),
                a.getQnaQ().getQnaQId()
        );
    }
}
