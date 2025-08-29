package com.and_backend.repository;

import com.and_backend.home.quest.QuestBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestBankRepository extends JpaRepository<QuestBank, Long> {
}
