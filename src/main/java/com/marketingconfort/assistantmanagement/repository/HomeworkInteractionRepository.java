package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.HomeworkInteraction;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.ProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HomeworkInteractionRepository extends JpaRepository<HomeworkInteraction, Long> {
    List<HomeworkInteraction> findByUserIdOrderByInteractionDateDesc(Long userId);
    List<HomeworkInteraction> findByAssistantIdAndInteractionDateBetween(Long assistantId, LocalDateTime startDate, LocalDateTime endDate);
    List<HomeworkInteraction> findByInteractionTypeAndIsRedirected(String interactionType, boolean isRedirected);
    List<HomeworkInteraction> findByProcessingStatus(ProcessingStatus status);
    long countByUserIdAndInteractionDateAfter(Long userId, LocalDateTime date);
}