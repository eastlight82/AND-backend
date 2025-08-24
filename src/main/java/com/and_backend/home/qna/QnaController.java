package com.and_backend.home.qna;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna") @RequiredArgsConstructor
public class QnaController {
    private final QnaService service;

    @PostMapping("/questions")
    public QnaQ addQuestion(@RequestBody QnaQ q) { return service.addQuestion(q); }

    @PostMapping("/questions/{qId}/answers")
    public QnaA addAnswer(@PathVariable Long qId, @RequestBody AnswerReq req) {
        return service.addAnswer(qId, req.content);
    }

    @GetMapping("/questions/{qId}/answers")
    public List<QnaA> answers(@PathVariable Long qId) { return service.answers(qId); }

    @Data
    static class AnswerReq { String content; }
}