package com.marketingconfort.assistantmanagement.service;

import com.marketingconfort.brainboost_common.assistant_devoir.models.ChildHomeworkAssistant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.EducationLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChildHomeworkAssistantService {

    // CRUD de base
    ChildHomeworkAssistant createAssistant(ChildHomeworkAssistant assistant);
    ChildHomeworkAssistant updateAssistant(Long assistantId, ChildHomeworkAssistant assistant);
    ChildHomeworkAssistant getAssistantDetails(Long assistantId);
    void deleteAssistant(Long assistantId);

    // Listing et Recherche
    Page<ChildHomeworkAssistant> listAssistants(int page, int size);
    Page<ChildHomeworkAssistant> searchAssistants(String keyword, Pageable pageable);

    // Fonctionnalités Métier
    String greetUser(Long userId, String educationalLevel);
    List<String> provideTips(String subject, String difficulty);
    String answerGeneralQuestion(String question, String context);
    Long redirectToOtherAssistant(Long currentAssistantId, String requestType);

    // Traitement Multimédia
    String handleMultimediaInput(MultipartFile multimediaInput, String inputType);
}