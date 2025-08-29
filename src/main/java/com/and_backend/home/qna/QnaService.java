package com.and_backend.home.qna;

import com.and_backend.home.qna.dto.AnswerCreateRequest;
import com.and_backend.home.qna.dto.AnswerResponse;
import com.and_backend.home.qna.dto.QuestionCreateRequest;
import com.and_backend.home.qna.dto.QuestionResponse;
import com.and_backend.home.quest.Quest;
import com.and_backend.home.quest.dto.QuestResponse;
import com.and_backend.lossCase.LossCase;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.QnaARepository;
import com.and_backend.repository.QnaQBankRepository;
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
    private final LossCaseRepository lRepo;
    private final QnaQBankRepository qBankRepo;

    public QuestionResponse addQuestion(QuestionCreateRequest req) {
        var lossCase= lRepo.findById(req.lossCaseId()).orElseThrow();
        var qnaQBank= qBankRepo.findById(req.qnaQBankId()).orElseThrow();

        var q = QnaQ.builder()
                .lossCase(lossCase)
                .qnaQBank(qnaQBank)
                .build();

        return QuestionResponse.from(qRepo.save(q));
    }

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
