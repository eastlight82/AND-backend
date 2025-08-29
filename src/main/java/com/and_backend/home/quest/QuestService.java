package com.and_backend.home.quest;

import com.and_backend.home.quest.dto.QuestCheckRequest;
import com.and_backend.home.quest.dto.QuestCreateRequest;
import com.and_backend.home.quest.dto.QuestResponse;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.QuestBankRepository;
import com.and_backend.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepo;
    private final LossCaseRepository lossCaseRepo;
    private final QuestBankRepository questBankRepo;

    public QuestResponse create(QuestCreateRequest req) { //기존 text대신 questBankId로
        var lossCase = lossCaseRepo.findById(req.lossCaseId()).orElseThrow();
        var questBank = questBankRepo.findById(req.questBankId()).orElseThrow();

        var quest = Quest.builder()
                .lossCase(lossCase)
                .questBank(questBank)
                .build();

        return QuestResponse.from(questRepo.save(quest));
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
