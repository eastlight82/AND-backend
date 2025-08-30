package com.and_backend.home.quest;

import com.and_backend.home.quest.dto.QuestCheckRequest;
import com.and_backend.home.quest.dto.QuestCreateRequest;
import com.and_backend.home.quest.dto.QuestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests") @RequiredArgsConstructor
public class QuestController {
    private final QuestService service;
    private final QuestService questService;

    @PostMapping
    public List<QuestResponse> create(@RequestBody QuestCreateRequest req) {
        return questService.createThreeQuests(req).stream()
                .map(q -> new QuestResponse(q.getQuestId(), q.getQuestBank().getText()))
                .toList();
    }

    @GetMapping
    public List<QuestResponse> get(@RequestParam Long lossCaseId){
        return service.listByLossCase(lossCaseId);
    }

    @DeleteMapping("/{questId}")
    public void delete(@PathVariable Long questId){
        service.delete(questId);
    }

    @PatchMapping("/{questId}")
    public QuestResponse toggleCompleted(@PathVariable Long questId, @RequestBody QuestCheckRequest req){
        return questService.toggle(questId, req);
    }

}