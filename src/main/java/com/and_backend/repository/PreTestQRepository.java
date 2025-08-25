package com.and_backend.repository;

import com.and_backend.preTest.PreTestQ;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreTestQRepository extends JpaRepository<PreTestQ, Long> {
    List<PreTestQ> findByLossCase_LossCaseId(Long lossCaseId);

    @EntityGraph(attributePaths = "options")
    List<PreTestQ> findWithOptionsByLossCase_LossCaseId(Long lossCaseId);
}
