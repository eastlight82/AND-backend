package com.and_backend.repository;

import com.and_backend.lossCase.LossCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LossCaseRepository extends JpaRepository<LossCase, Long> {
    List<LossCase> findAllByUsers_UsersId(Long usersId);
}
