package com.and_backend.lossCase;

import com.and_backend.enums.*;
import com.and_backend.lossCase.dto.LossCaseCreateRequest;
import com.and_backend.lossCase.dto.LossCaseDto;
import com.and_backend.lossCase.dto.LossCaseUpdateRequest;
import com.and_backend.repository.LossCaseRepository;
import com.and_backend.repository.UsersRepository;
import com.and_backend.users.Users;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase // H2 사용
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@org.springframework.test.context.ActiveProfiles("test")
class LossCaseServiceTest {

    @Autowired LossCaseService lossCaseService;
    @Autowired UsersRepository usersRepository;
    @Autowired LossCaseRepository lossCaseRepository;

    private Long userId;

    @BeforeEach
    void setUp() {
        Users u = new Users();
        // TODO: Users에 NotNull 필드가 있으면 세팅
        // u.setEmail("tester@example.com"); ...
        usersRepository.save(u);
        userId = u.getUsersId();
        assertThat(userId).isNotNull();
    }

    @Test
    @Transactional
    void create_and_getAll_and_getOne() throws Exception {
        // given
        String photoB64 = Base64.getEncoder().encodeToString("img".getBytes());
        var req = new LossCaseCreateRequest(
                LossSubject.PET, WithTime.LESS_6M, LocalDate.of(2024,5,1),
                LossReason.MISSING, CopeWay.SUPPRESS, null,
                photoB64
        );

        // when
        LossCaseDto created = lossCaseService.create(userId, req);

        // then
        assertThat(created.lossCaseId()).isNotNull();

        List<LossCaseDto> all = lossCaseService.getAll(userId);
        assertThat(all).hasSize(1);

        LossCaseDto one = lossCaseService.getOne(userId, created.lossCaseId());
        assertThat(one.subject()).isEqualTo(LossSubject.PET);
    }

    @Test
    @Transactional
    void update_photoRules_and_delete() throws Exception {
        // 1) seed
        var created = lossCaseService.create(userId, new LossCaseCreateRequest(
                LossSubject.FAMILY, WithTime.LESS_6M, LocalDate.of(2023,1,2),
                LossReason.NATURAL_DRIFT, CopeWay.SUPPRESS, null, null
        ));
        Long id = created.lossCaseId();

        // 2) blank → 기존 사진 유지 (현재 사진 없음 → 그대로 null)
        var blankReq = new LossCaseUpdateRequest(null,null,null,null,null, null,"   ");
        lossCaseService.update(userId, id, blankReq);
        assertThat(lossCaseRepository.findById(id).orElseThrow().getPhoto()).isNull();

        // 3) 값 제공 → 교체
        String b64 = Base64.getEncoder().encodeToString("new".getBytes());
        var replaceReq = new LossCaseUpdateRequest(
                LossSubject.FAMILY, WithTime.LESS_6M, LocalDate.of(2023,1,2),
                LossReason.NATURAL_DRIFT, CopeWay.SUPPRESS, null, null
        );
        lossCaseService.update(userId, id, replaceReq);
        assertThat(new String(lossCaseRepository.findById(id).orElseThrow().getPhoto())).isEqualTo("new");

        // 4) "null" → 제거
        var removeReq = new LossCaseUpdateRequest(null,null,null,null,null,null,"null");
        lossCaseService.update(userId, id, removeReq);
        assertThat(lossCaseRepository.findById(id).orElseThrow().getPhoto()).isNull();

        // 5) delete
        lossCaseService.delete(userId, id);
        assertThat(lossCaseRepository.findById(id)).isEmpty();
    }

    @Test
    void getOne_otherUser_denied() {
        // seed by user A
        var created = lossCaseService.create(userId, new LossCaseCreateRequest(
                LossSubject.FAMILY, WithTime.LESS_6M, LocalDate.of(2023,1,2),
                LossReason.NATURAL_DRIFT, CopeWay.SUPPRESS, null, null
        ));

        // user B
        Users other = new Users();
        usersRepository.save(other);

        assertThatThrownBy(() -> lossCaseService.getOne(other.getUsersId(), created.lossCaseId()))
                .isInstanceOf(AccessDeniedException.class);
    }
}
