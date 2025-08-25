package com.and_backend.repository;

import com.and_backend.home.quest.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> findByLossCase_LossCaseId(Long lossCaseId);
}
