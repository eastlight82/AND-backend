package com.and_backend.home.qna;

import com.and_backend.home.qna.dto.AnswerCreateRequest;
import com.and_backend.home.qna.dto.AnswerResponse;
import com.and_backend.home.qna.dto.QuestionResponse;
import com.and_backend.home.qna.dto.QuestionCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna") @RequiredArgsConstructor
public class QnaController {
    private final QnaService service;

    @PostMapping("/questions")
    public QuestionResponse createQuestion(@RequestBody @Valid QuestionCreateRequest req) { return service.createQuestion(req); }

    @GetMapping("/questions")
    public QuestionResponse getQuestion(@RequestParam Long lossCaseId) {
        return service.getQuestionByLossCase(lossCaseId);
    }

    @PostMapping("/questions/{qId}/answers")
    public AnswerResponse addAnswer(@PathVariable Long qId, @RequestBody @Valid AnswerCreateRequest req) {
        return service.addAnswer(qId, req);
    }

    @GetMapping("/questions/{qId}/answers")
    public List<AnswerResponse> answers(@PathVariable Long qId) { return service.answers(qId); }
}