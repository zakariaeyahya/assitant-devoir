package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.ChildHomeworkInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildInterfaceRepository extends JpaRepository<ChildHomeworkInterface, Long> {
    Optional<ChildHomeworkInterface> findByAssistantId(Long assistantId);
    List<ChildHomeworkInterface> findByWelcomeMessageContaining(String keyword);
}