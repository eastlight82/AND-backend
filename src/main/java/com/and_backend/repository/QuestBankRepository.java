package com.and_backend.repository;

import com.and_backend.home.quest.QuestBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestBankRepository extends JpaRepository<QuestBank, Long> {
    @Query(value = """
    (SELECT * FROM quest_bank
    WHERE (:subject  IS NULL OR subject  = :subject) ORDER BY random() LIMIT 2)
    UNION ALL
    (SELECT * FROM quest_bank
    WHERE (:copeWay  IS NULL OR copeway = :copeWay)
    ORDER BY random() LIMIT 1)
  """, nativeQuery = true)
    List<QuestBank> pickRandom3(
            @Param("subject")  String subject,
            @Param("copeWay")  String copeWay
    );
}
