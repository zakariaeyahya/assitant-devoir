package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.InteractionDevoir;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.StatusTraitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InteractionDevoirRepository extends JpaRepository<InteractionDevoir, Long> {
    List<InteractionDevoir> findByUserIdOrderByInteractionDateDesc(Long userId);
    List<InteractionDevoir> findByAssistantIdAndInteractionDateBetween(Long assistantId, LocalDateTime startDate, LocalDateTime endDate);
    List<InteractionDevoir> findByInteractionTypeAndIsRedirected(String interactionType, boolean isRedirected);
    List<InteractionDevoir> findByProcessingStatus(StatusTraitement status);
    long countByUserIdAndInteractionDateAfter(Long userId, LocalDateTime date);
}