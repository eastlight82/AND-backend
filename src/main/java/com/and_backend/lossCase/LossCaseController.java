package com.and_backend.lossCase;

import com.and_backend.lossCase.dto.LossCaseCreateRequest;
import com.and_backend.lossCase.dto.LossCaseDto;
import com.and_backend.lossCase.dto.LossCaseUpdateRequest;
import com.and_backend.users.security.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/loss-cases")
@RequiredArgsConstructor
public class LossCaseController {

    private final LossCaseService lossCaseService;

    // 생성
    @PostMapping
    public ResponseEntity<LossCaseDto> create(
            @AuthenticationPrincipal CustomUser principal,
            @RequestBody @Valid LossCaseCreateRequest req
    ) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LossCaseDto saved = lossCaseService.create(principal.getUsersId(), req);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<LossCaseDto> getOne(
            @AuthenticationPrincipal CustomUser principal,
            @PathVariable Long id
    ) throws AccessDeniedException {
        LossCaseDto dto = lossCaseService.getOne(principal.getUsersId(), id);
        return ResponseEntity.ok(dto);
    }

    // 사용자 본인 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<LossCaseDto>> getAll(
            @AuthenticationPrincipal CustomUser principal
    ) {
        List<LossCaseDto> list = lossCaseService.getAll(principal.getUsersId());
        return ResponseEntity.ok(list);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<LossCaseDto> update(
            @AuthenticationPrincipal CustomUser principal,
            @PathVariable Long id,
            @RequestBody LossCaseUpdateRequest req
    ) throws AccessDeniedException {
        LossCaseDto updated = lossCaseService.update(principal.getUsersId(), id, req);
        return ResponseEntity.ok(updated);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUser principal,
            @PathVariable Long id
    ) throws AccessDeniedException {
        lossCaseService.delete(principal.getUsersId(), id);
        return ResponseEntity.noContent().build();
    }
}
