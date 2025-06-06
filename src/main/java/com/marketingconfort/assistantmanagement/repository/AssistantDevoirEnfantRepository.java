package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.AssistantDevoirEnfant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.NiveauEducatif;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssistantDevoirEnfantRepository extends JpaRepository<AssistantDevoirEnfant, Long> {
    List<AssistantDevoirEnfant> findByUserIdAndEnabled(Long userId, boolean enabled);
    List<AssistantDevoirEnfant> findByEducationLevel(NiveauEducatif educationLevel);
    List<AssistantDevoirEnfant> findByStatus(AssistantStatus status);
    Optional<AssistantDevoirEnfant> findByUserIdAndType(Long userId, String type);
}