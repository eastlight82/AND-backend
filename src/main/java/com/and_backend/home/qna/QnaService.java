package com.and_backend.home.qna;

import com.and_backend.home.qna.dto.AnswerCreateRequest;
import com.and_backend.home.qna.dto.AnswerResponse;
import com.and_backend.home.qna.dto.QuestionCreateRequest;
import com.and_backend.home.qna.dto.QuestionResponse;
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

    @Transactional
    public QuestionResponse createQuestion(QuestionCreateRequest req) {
        var lossCase= lRepo.findById(req.lossCaseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상실 케이스입니다."));
        var bank= qBankRepo.pickRandom(lossCase.getSubject()!=null ? lossCase.getSubject().name() : null);

        QnaQ q = QnaQ.builder()
                .qnaQBank(bank)
                .text(bank.getText())
                .subject(bank.getSubject())
                .lossCase(lossCase)
                .build();

        return QuestionResponse.from(qRepo.save(q));
    }

    public QuestionResponse getQuestionByLossCase(Long lossCaseId){
        QnaQ qnaq= qRepo.findByLossCase_LossCaseId(lossCaseId);

        return QuestionResponse.from(qnaq);
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
