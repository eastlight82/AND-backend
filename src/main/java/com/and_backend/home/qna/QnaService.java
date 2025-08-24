package com.and_backend.home.qna;

import com.and_backend.repository.QnaARepository;
import com.and_backend.repository.QnaQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaQRepository qRepo;
    private final QnaARepository aRepo;

    public QnaQ addQuestion(QnaQ q) { return qRepo.save(q); }
    public QnaA addAnswer(Long qId, String content) {
        QnaQ q = qRepo.findById(qId).orElseThrow();
        return aRepo.save(QnaA.builder().qnaQ(q).content(content).build());
    }
    public List<QnaA> answers(Long qId) {
        QnaQ q = qRepo.findById(qId).orElseThrow();
        return aRepo.findAll().stream().filter(a -> a.getQnaQ().getQnaQId().equals(q.getQnaQId())).toList();
    }
}
