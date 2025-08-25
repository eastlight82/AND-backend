package com.and_backend.home.diary;

import com.and_backend.home.diary.dto.DiaryCreateRequest;
import com.and_backend.home.diary.dto.DiaryResponse;
import com.and_backend.home.diary.Diary;
import com.and_backend.repository.DiaryRepository;
import com.and_backend.repository.LossCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepo;
    private final LossCaseRepository lossCaseRepo;

    public DiaryResponse create(DiaryCreateRequest req){
        var lc = lossCaseRepo.findById(req.lossCaseId()).orElseThrow();
        var diary = diaryRepo.save(Diary.builder().lossCase(lc).text(req.text()).build()); //Repo generic 잘못설정
        return DiaryResponse.from(diary);
    }

    public List<DiaryResponse> list(Long lossCaseId){
        return diaryRepo.findByLossCase_LossCaseIdOrderByCreatedAtDesc(lossCaseId)
                .stream().map(DiaryResponse::from).toList();
    }

    public void delete(Long diaryId){ diaryRepo.deleteById(diaryId); }
}
