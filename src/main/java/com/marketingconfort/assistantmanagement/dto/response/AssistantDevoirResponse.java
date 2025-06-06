package com.marketingconfort.assistantmanagement.dto.response;

import com.marketingconfort.brainboost_common.assistant_devoir.enums.NiveauEducatif;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssistantDevoirResponse {

    private Long id;
    private Long userId;
    private String type;
    private AssistantStatus status;
    private NiveauEducatif educationLevel;
    private Boolean enabled;
    private List<String> supportedInputTypes;

    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private Long creatorId;
    private Long lastModifierId;
}