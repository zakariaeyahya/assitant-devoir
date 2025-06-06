package com.marketingconfort.assistantmanagement.dto.request;

import com.marketingconfort.brainboost_common.assistant_devoir.enums.EducationLevel;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class HomeworkAssistantRequest {

    @NotNull(message = "L'ID utilisateur est requis")
    private Long userId;

    @NotBlank(message = "Le type est requis")
    private String type;

    private AssistantStatus status = AssistantStatus.ACTIVE;

    @NotNull(message = "Le niveau éducatif est requis")
    private EducationLevel educationLevel;

    private Boolean isActive = true;  // CHANGER: enabled → isActive

    private List<String> supportedInputTypes;
}