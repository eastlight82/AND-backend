package com.and_backend.preTest;

import com.and_backend.preTest.dto.PreTestListResponse;
import com.and_backend.preTest.dto.PreTestSubmitRequest;
import com.and_backend.preTest.dto.PreTestSubmitResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pretest") @RequiredArgsConstructor
public class PreTestController {
    private final PreTestService service;

    // 문항+선택지 로드
    @GetMapping("/questions")
    public PreTestListResponse load(@RequestParam Long lossCaseId){
        return service.loadQuestions(lossCaseId);
    }

    // 제출
    @PostMapping("/submit")
    public PreTestSubmitResponse submit(@RequestBody @Valid PreTestSubmitRequest req){
        return service.submit(req);
    }
}
