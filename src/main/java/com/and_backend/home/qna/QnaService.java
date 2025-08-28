package com.and_backend.home.qna;

import com.and_backend.home.qna.dto.AnswerCreateRequest;
import com.and_backend.home.qna.dto.AnswerResponse;
import com.and_backend.home.qna.dto.QuestionCreateRequest;
import com.and_backend.home.qna.dto.QuestionResponse;
import com.and_backend.lossCase.LossCase;
import com.and_backend.repository.QnaARepository;
import com.and_backend.repository.QnaQRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaQRepository qRepo;
    private final QnaARepository aRepo;

    public QuestionResponse addQuestion(QuestionCreateRequest req) {
        var q = QnaQ.builder()
            .text(req.text())
            .lossCase(new LossCase(req.lossCaseId())) // FK만 세팅 (엔티티 new 로 id만 주입)
            .build();
        return QuestionResponse.from(qRepo.save(q)); }

    public AnswerResponse addAnswer(Long qId, AnswerCreateRequest req) {
        var q = qRepo.findById(qId)
                .orElseThrow(() -> new EntityNotFoundException("Question %d not found".formatted(qId)));

        var a = QnaA.builder()
                .qnaQ(q)
                .text(req.text())
                .build();

        return AnswerResponse.from(aRepo.save(a));
    }

    @Transactional
    public List<AnswerResponse> answers(Long qId) {
        var q = qRepo.findById(qId)
                .orElseThrow(() -> new EntityNotFoundException("Question %d not found".formatted(qId)));

        return aRepo.findAll().stream()
                .filter(a -> a.getQnaQ().getQnaQId().equals(q.getQnaQId()))
                .map(AnswerResponse::from)
                .toList();
    }
}
