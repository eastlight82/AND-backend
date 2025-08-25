package com.and_backend.home.quest;

import com.and_backend.home.quest.dto.QuestCreateRequest;
import com.and_backend.home.quest.dto.QuestResponse;
import com.and_backend.home.quest.dto.QuestUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests") @RequiredArgsConstructor
public class QuestController {
    private final QuestService service;

    @PostMapping
    public QuestResponse create(@RequestBody @Valid QuestCreateRequest req){
        return service.create(req);
    }

    @GetMapping
    public List<QuestResponse> list(@RequestParam Long lossCaseId){
        return service.listByLossCase(lossCaseId);
    }

    @PutMapping("/{questId}")
    public QuestResponse update(@PathVariable Long questId, @RequestBody QuestUpdateRequest req){
        return service.update(questId, req);
    }

    @DeleteMapping("/{questId}")
    public void delete(@PathVariable Long questId){
        service.delete(questId);
    }
}