package com.and_backend.home.quest;

import com.and_backend.home.quest.dto.QuestCheckRequest;
import com.and_backend.home.quest.dto.QuestCreateRequest;
import com.and_backend.home.quest.dto.QuestResponse;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.QuestBankRepository;
import com.and_backend.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepo;
    private final LossCaseRepository lossCaseRepo;
    private final QuestBankRepository questBankRepo;

    private String emptyToNull(String str) {
        return str == null || str.trim().isEmpty() ? null : str;
    }

    @Transactional
    public List<Quest> createThreeQuests(QuestCreateRequest req) {
        var banks = questBankRepo.pickRandom3(req.subject(), req.copeWay());

        if (banks.size() < 3) {
            throw new IllegalStateException("조건에 맞는 템플릿이 3개 미만입니다.");
        }

        var lossCase = lossCaseRepo.findById(req.lossCaseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상실 케이스입니다."));

        List<Quest> quests = banks.stream()
                .map(qb -> Quest.builder()
                        .questBank(qb)
                        .text(qb.getText())
                        .subject(qb.getSubject())
                        .copeway(qb.getCopeWay())
                        .completed(false)
                        .lossCase(lossCase)
                        .build())
                .toList();

        return questRepo.saveAll(quests);
    }
    public List<QuestResponse> listByLossCase(Long lossCaseId){
        return questRepo.findByLossCase_LossCaseId(lossCaseId).stream().map(QuestResponse::from).toList();
    }

    public QuestResponse toggle(Long questId, QuestCheckRequest req){
        var q= questRepo.findById(questId).orElseThrow();
        q.toggleCompleted();

        return QuestResponse.from(questRepo.save(q));
    }

    public void delete(Long questId){ questRepo.deleteById(questId); }
}
