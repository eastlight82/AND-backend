package com.and_backend.repository;

import com.and_backend.lossCase.LossCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LossCaseRepository extends JpaRepository<LossCase, Long> {}
