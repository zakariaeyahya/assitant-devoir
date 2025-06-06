package com.marketingconfort.assistantmanagement.repository;

import com.marketingconfort.brainboost_common.assistant_devoir.models.HistoriqueInteractionsDevoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterfaceInteractionHistoryRepository extends JpaRepository<HistoriqueInteractionsDevoir, Long> {
    Optional<HistoriqueInteractionsDevoir> findByUserId(Long userId);
    List<HistoriqueInteractionsDevoir> findByCreatedAtAfter(LocalDateTime date);
}