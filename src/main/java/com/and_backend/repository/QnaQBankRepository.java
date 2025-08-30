package com.and_backend.repository;

import com.and_backend.enums.LossSubject;
import com.and_backend.home.qna.QnaQBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QnaQBankRepository extends JpaRepository<QnaQBank, Long> {
    @Query(value= """
    SELECT * FROM qnaq_bank
    WHERE (:subject IS NULL OR subject= :subject) ORDER BY random() LIMIT 1
    """, nativeQuery=true)

    QnaQBank pickRandom(
              @Param("subject") String subject
    );
}
