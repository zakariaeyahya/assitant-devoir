package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.InterfaceDevoirEnfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterfaceDevoirEnfantRepository extends JpaRepository<InterfaceDevoirEnfant, Long> {
    Optional<InterfaceDevoirEnfant> findByAssistantId(Long assistantId);
    List<InterfaceDevoirEnfant> findByWelcomeMessageContaining(String keyword);
}