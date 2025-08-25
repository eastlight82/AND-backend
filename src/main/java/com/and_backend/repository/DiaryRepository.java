package com.and_backend.repository;

import com.and_backend.home.chat.ChatRoom;
import com.and_backend.home.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByLossCase_LossCaseIdOrderByCreatedAtDesc(Long lossCaseId);
}
