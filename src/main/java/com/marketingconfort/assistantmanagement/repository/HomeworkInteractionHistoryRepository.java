package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.HomeworkInteractionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomeworkInteractionHistoryRepository extends JpaRepository<HomeworkInteractionHistory, Long> {
    Optional<HomeworkInteractionHistory> findByUserId(Long userId);
    List<HomeworkInteractionHistory> findByCreatedAtAfter(LocalDateTime date);
}