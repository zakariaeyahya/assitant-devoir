package com.marketingconfort.assistantmanagement.service;

import com.marketingconfort.brainboost_common.assistant_devoir.models.AssistantDevoirEnfant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.NiveauEducatif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssistantDevoirEnfantService {

    // CRUD de base
    AssistantDevoirEnfant createAssistant(AssistantDevoirEnfant assistant);
    AssistantDevoirEnfant updateAssistant(Long assistantId, AssistantDevoirEnfant assistant);
    AssistantDevoirEnfant getAssistantDetails(Long assistantId);
    void deleteAssistant(Long assistantId);

    // Listing et Recherche
    Page<AssistantDevoirEnfant> listAssistants(int page, int size);
    Page<AssistantDevoirEnfant> searchAssistants(String keyword, Pageable pageable);

    // Fonctionnalités Métier
    String greetUser(Long userId, String educationalLevel);
    List<String> provideTips(String subject, String difficulty);
    String answerGeneralQuestion(String question, String context);
    Long redirectToOtherAssistant(Long currentAssistantId, String requestType);

    // Traitement Multimédia
    String handleMultimediaInput(MultipartFile multimediaInput, String inputType);
}