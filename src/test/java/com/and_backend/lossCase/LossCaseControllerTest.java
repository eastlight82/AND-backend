package com.and_backend.lossCase;

import com.and_backend.enums.*;
import com.and_backend.lossCase.dto.*;
import com.and_backend.users.security.CustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LossCaseController.class)
@AutoConfigureMockMvc(addFilters = false)
class LossCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LossCaseService lossCaseService;

    private CustomUser user;
    private UsernamePasswordAuthenticationToken auth;

    @BeforeEach
    void setup() {
        user = new CustomUser(1L, "user@example.com", "password", Collections.emptyList());
        auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Test
    void create_shouldReturnCreatedWithDto() throws Exception {
        LossCaseCreateRequest req = new LossCaseCreateRequest(
                LossSubject.LOVER,
                WithTime.LESS_6M,
                LocalDate.of(2024,1,1),
                LossReason.PARTNER_LOST_FEELINGS,
                CopeWay.SUPPRESS,
                null,
                null
        );
        LossCaseDto dto = new LossCaseDto(
                1L,
                req.subject(),
                req.withTime(),
                req.lossDate(),
                req.lossReason(),
                req.copeWay(),
                req.lossSubjectFamily()
        );
        when(lossCaseService.create(eq(user.getUsersId()), any(LossCaseCreateRequest.class))).thenReturn(dto);

        mockMvc.perform(post("/loss-cases")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void getOne_shouldReturnCase() throws Exception {
        long id = 1L;
        LossCaseDto dto = new LossCaseDto(
                id,
                LossSubject.LOVER,
                WithTime.LESS_6M,
                LocalDate.of(2024,1,1),
                LossReason.PARTNER_LOST_FEELINGS,
                CopeWay.SUPPRESS,
                null
        );
        when(lossCaseService.getOne(user.getUsersId(), id)).thenReturn(dto);

        mockMvc.perform(get("/loss-cases/{id}", id)
                        .with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void getAll_shouldReturnList() throws Exception {
        LossCaseDto dto1 = new LossCaseDto(
                1L,
                LossSubject.LOVER,
                WithTime.LESS_6M,
                LocalDate.of(2024,1,1),
                LossReason.PARTNER_LOST_FEELINGS,
                CopeWay.SUPPRESS,
                null
        );
        LossCaseDto dto2 = new LossCaseDto(
                2L,
                LossSubject.PET,
                WithTime.M6_TO_1Y,
                LocalDate.of(2023,12,1),
                LossReason.NATURAL_DEATH,
                CopeWay.EXPRESS,
                LossSubjectFamily.DAUGHTER
        );
        List<LossCaseDto> list = List.of(dto1, dto2);
        when(lossCaseService.getAll(user.getUsersId())).thenReturn(list);

        mockMvc.perform(get("/loss-cases")
                        .with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void update_shouldReturnUpdatedCase() throws Exception {
        long id = 1L;
        LossCaseUpdateRequest req = new LossCaseUpdateRequest(
                LossSubject.FRIEND,
                WithTime.OVER_10Y,
                LocalDate.of(2022,5,5),
                LossReason.FIGHT,
                CopeWay.ANALYZE,
                null,
                null
        );
        LossCaseDto dto = new LossCaseDto(
                id,
                req.subject(),
                req.withTime(),
                req.lossDate(),
                req.lossReason(),
                req.copeWay(),
                req.lossSubjectFamily()
        );
        when(lossCaseService.update(eq(user.getUsersId()), eq(id), any(LossCaseUpdateRequest.class))).thenReturn(dto);

        mockMvc.perform(put("/loss-cases/{id}", id)
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void delete_shouldReturnNoContent() throws Exception {
        long id = 1L;
        doNothing().when(lossCaseService).delete(user.getUsersId(), id);

        mockMvc.perform(delete("/loss-cases/{id}", id)
                        .with(authentication(auth)))
                .andExpect(status().isNoContent());

        verify(lossCaseService).delete(user.getUsersId(), id);
    }
}

