package com.and_backend.home.diary;

import com.and_backend.home.diary.dto.DiaryCreateRequest;
import com.and_backend.home.diary.dto.DiaryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries") @RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public DiaryResponse create(@RequestBody @Valid DiaryCreateRequest req){
        return diaryService.create(req);
    }

    @GetMapping
    public List<DiaryResponse> list(@RequestParam Long lossCaseId){
        return diaryService.list(lossCaseId);
    }

    @DeleteMapping("/{diaryId}")
    public void delete(@PathVariable Long diaryId){
        diaryService.delete(diaryId);
    }
}