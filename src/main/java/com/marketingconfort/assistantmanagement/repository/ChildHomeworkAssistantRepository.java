package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.ChildHomeworkAssistant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.EducationLevel;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildHomeworkAssistantRepository extends JpaRepository<ChildHomeworkAssistant, Long> {
    List<ChildHomeworkAssistant> findByUserIdAndEnabled(Long userId, boolean enabled);
    List<ChildHomeworkAssistant> findByEducationLevel(EducationLevel educationLevel);
    List<ChildHomeworkAssistant> findByStatus(AssistantStatus status);
    Optional<ChildHomeworkAssistant> findByUserIdAndType(Long userId, String type);
}