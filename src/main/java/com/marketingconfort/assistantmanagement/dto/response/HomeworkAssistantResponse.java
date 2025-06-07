package com.marketingconfort.assistantmanagement.dto.response;

import com.marketingconfort.brainboost_common.assistant_devoir.enums.EducationLevel;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HomeworkAssistantResponse {

    private Long id;
    private Long userId;
    private String type;
    private AssistantStatus status;
    private EducationLevel educationLevel;
    private Boolean isActive;
    private List<String> supportedInputTypes;

    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private Long creatorId;
    private Long lastModifierId;
}