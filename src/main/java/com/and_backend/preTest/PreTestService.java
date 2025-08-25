package com.and_backend.preTest;

import com.and_backend.preTest.dto.PreTestListResponse;
import com.and_backend.preTest.dto.PreTestQuestionRes;
import com.and_backend.preTest.dto.PreTestSubmitRequest;
import com.and_backend.preTest.dto.PreTestSubmitResponse;
import com.and_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreTestService {
    private final PreTestQRepository qRepo;
    private final PreTestOptRepository optRepo;
    private final LossCaseRepository lossCaseRepo;

    public PreTestListResponse loadQuestions(Long lossCaseId){
        // lossCase 존재 여부 확인
        lossCaseRepo.findById(lossCaseId).orElseThrow();

        // ✅ N+1을 피하고 싶으면 아래 라인 사용
        var qs = qRepo.findWithOptionsByLossCase_LossCaseId(lossCaseId);

        // (기존 findByLossCase_LossCaseId를 계속 쓰고 싶다면 그대로 두어도 되지만, 옵션 접근 시 N+1 발생)
        // var qs = qRepo.findByLossCase_LossCaseId(lossCaseId);

        var res = qs.stream().map(PreTestQuestionRes::from).toList();
        return new PreTestListResponse(lossCaseId, res);
    }

    public PreTestSubmitResponse submit(PreTestSubmitRequest req){
        // 간단 로직: 응답한 questionId의 score 합산
        var byId = qRepo.findByLossCase_LossCaseId(req.lossCaseId())
                .stream().collect(Collectors.toMap(PreTestQ::getPreTestQId, q->q));

        int total = 0;
        int answered = 0;
        for(var a : req.answers()){
            var q = byId.get(a.questionId());
            if(q != null){
                total += (q.getScore() != null ? q.getScore() : 0);
                answered++;
            }
        }
        return new PreTestSubmitResponse(req.lossCaseId(), answered, total);
    }
}
