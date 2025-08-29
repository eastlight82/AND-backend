package com.and_backend.lossCase;

import com.and_backend.lossCase.dto.LossCaseCreateRequest;
import com.and_backend.lossCase.dto.LossCaseDto;
import com.and_backend.lossCase.dto.LossCaseUpdateRequest;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.UsersRepository;
import com.and_backend.users.Users;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LossCaseService {
    private final LossCaseRepository lossCaseRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public LossCaseDto create(Long usersId, LossCaseCreateRequest req) {
        Users user = usersRepository.getReferenceById(usersId);

        LossCase lc= new LossCase(); //create: dto로 받은걸 객체 생성
        lc.setSubject(req.subject());
        lc.setWithTime(req.withTime());
        lc.setLossDate(req.lossDate());
        lc.setLossReason(req.lossReason());
        lc.setCopeWay(req.copeWay());
        lc.setUsers(user);
        lc.setSubjectFamily(req.lossSubjectFamily());

        if (req.photoBase64() != null && !req.photoBase64().isBlank()) {
            lc.setPhoto(Base64.getDecoder().decode(req.photoBase64()));
        }
        LossCase saved= lossCaseRepository.save(lc);

        return LossCaseDto.of(saved);
    }

    @Transactional(readOnly = true)
    public LossCaseDto getOne(Long userId, Long lossCaseId) throws AccessDeniedException {
        LossCase lc = lossCaseRepository.findById(lossCaseId)
                .orElseThrow(() -> new IllegalArgumentException("LossCase not found"));
        if (!lc.getUsers().getUsersId().equals(userId)) {
            throw new AccessDeniedException("다른 사람의 데이터를 조회할 수 없습니다.");
        }
        return LossCaseDto.of(lc);
    }

    @Transactional(readOnly = true)
    public List<LossCaseDto> getAll(Long userId) {
        return lossCaseRepository.findAllByUsers_UsersId(userId)
                .stream()
                .map(LossCaseDto::of)
                .toList();
    }

    public LossCaseDto update(Long userId, Long lossCaseId, LossCaseUpdateRequest req) throws AccessDeniedException {
        Users user = usersRepository.getReferenceById(userId);
        LossCase lc = lossCaseRepository.findById(lossCaseId)
                        .orElseThrow(()-> new IllegalArgumentException(lossCaseId+"lossCase not found"));

        if (!lc.getUsers().getUsersId().equals(user.getUsersId())){
            throw new AccessDeniedException("자신의 lossCase만 수정 가능합니다");
        }

        if (req.subject() != null) lc.setSubject(req.subject());
        if (req.withTime() != null) lc.setWithTime(req.withTime());
        if (req.lossDate() != null) lc.setLossDate(req.lossDate());
        if (req.lossReason() != null) lc.setLossReason(req.lossReason());
        if (req.copeWay() != null) lc.setCopeWay(req.copeWay());
        lc.setSubjectFamily(req.lossSubjectFamily());

        if (req.photoBase64() != null) {
            if (req.photoBase64().isBlank()) {
                // 빈 문자열 → 기존 사진 유지 (아무 처리 안 함)
            } else {
                // 값이 있으면 교체, "null" 명시했으면 제거
                if (req.photoBase64().equalsIgnoreCase("null")) {
                    lc.setPhoto(null);
                } else {
                    lc.setPhoto(Base64.getDecoder().decode(req.photoBase64()));
                }
            }
        }

        LossCase saved= lossCaseRepository.save(lc);

        return LossCaseDto.of(saved);
    }
    @Transactional
    public void delete(Long userId, Long lossCaseId) throws AccessDeniedException {
        LossCase lc = lossCaseRepository.findById(lossCaseId)
                .orElseThrow(() -> new IllegalArgumentException("LossCase not found"));

        if (!lc.getUsers().getUsersId().equals(userId)) {
            throw new AccessDeniedException("자신의 lossCase만 삭제 가능합니다.");
        }

        lossCaseRepository.delete(lc);
    }
    //chatroom용
    @Transactional(readOnly = true)
    public LossCase get(Long lossCaseId) {
        return lossCaseRepository.findById(lossCaseId)
                .orElseThrow(() -> new IllegalArgumentException("LossCase not found"));
    }

}
