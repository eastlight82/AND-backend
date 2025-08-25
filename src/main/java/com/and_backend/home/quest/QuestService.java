package com.and_backend.home.quest;

import com.and_backend.home.quest.dto.QuestCreateRequest;
import com.and_backend.home.quest.dto.QuestResponse;
import com.and_backend.home.quest.dto.QuestUpdateRequest;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepo;
    private final LossCaseRepository lossCaseRepo;

    public QuestResponse create(QuestCreateRequest req){
        var lc = lossCaseRepo.findById(req.lossCaseId()).orElseThrow();
        var q = questRepo.save(Quest.builder().lossCase(lc).text(req.text()).build());
        return QuestResponse.from(q);
    }

    public List<QuestResponse> listByLossCase(Long lossCaseId){
        return questRepo.findByLossCase_LossCaseId(lossCaseId).stream().map(QuestResponse::from).toList();
    }

    public QuestResponse update(Long questId, QuestUpdateRequest req){
        var q = questRepo.findById(questId).orElseThrow();
        q.setText(req.text());
        return QuestResponse.from(questRepo.save(q));
    }

    public void delete(Long questId){ questRepo.deleteById(questId); }
}
