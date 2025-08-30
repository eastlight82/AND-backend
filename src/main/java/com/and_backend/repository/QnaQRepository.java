package com.and_backend.repository;

import com.and_backend.home.qna.QnaQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface QnaQRepository extends JpaRepository<QnaQ, Long> {
    QnaQ findByLossCase_LossCaseId(Long lossCaseId);
}
