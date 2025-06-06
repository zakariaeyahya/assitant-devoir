package com.marketingconfort.assistantmanagement.service.impl;

import com.marketingconfort.assistantmanagement.repository.AssistantDevoirEnfantRepository;
import com.marketingconfort.assistantmanagement.service.AssistantDevoirEnfantService;
import com.marketingconfort.brainboost_common.assistant_devoir.models.AssistantDevoirEnfant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssistantDevoirEnfantServiceImpl implements AssistantDevoirEnfantService {

    private final AssistantDevoirEnfantRepository assistantRepository;

    @Override
    public AssistantDevoirEnfant createAssistant(AssistantDevoirEnfant assistant) {
        log.info("Création d'un nouvel assistant pour l'utilisateur: {}", assistant.getUserId());

        // Validation simple
        if (assistant.getUserId() == null) {
            throw new RuntimeException("L'ID utilisateur est requis");
        }

        // Valeurs par défaut
        assistant.setStatus(AssistantStatus.ACTIVE);
        assistant.setEnabled(true);
        assistant.setCreatorId(1L);
        assistant.setLastModifierId(1L);

        return assistantRepository.save(assistant);
    }

    @Override
    public AssistantDevoirEnfant updateAssistant(Long assistantId, AssistantDevoirEnfant assistant) {
        log.info("Mise à jour de l'assistant: {}", assistantId);

        AssistantDevoirEnfant existing = getAssistantDetails(assistantId);

        // Mise à jour des champs
        existing.setType(assistant.getType());
        existing.setStatus(assistant.getStatus());
        existing.setEducationLevel(assistant.getEducationLevel());
        existing.setEnabled(assistant.getEnabled());
        existing.setLastModifierId(1L);

        return assistantRepository.save(existing);
    }

    @Override
    public AssistantDevoirEnfant getAssistantDetails(Long assistantId) {
        return assistantRepository.findById(assistantId)
                .orElseThrow(() -> new RuntimeException("Assistant non trouvé: " + assistantId));
    }

    @Override
    public void deleteAssistant(Long assistantId) {
        log.info("Suppression de l'assistant: {}", assistantId);

        AssistantDevoirEnfant assistant = getAssistantDetails(assistantId);
        assistantRepository.delete(assistant);
    }

    @Override
    public Page<AssistantDevoirEnfant> listAssistants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return assistantRepository.findAll(pageable);
    }

    @Override
    public Page<AssistantDevoirEnfant> searchAssistants(String keyword, Pageable pageable) {
        // Recherche simple (à améliorer avec critères)
        return assistantRepository.findAll(pageable);
    }

    @Override
    public String greetUser(Long userId, String educationalLevel) {
        log.info("Configuration du message d'accueil pour l'utilisateur: {} - niveau: {}", userId, educationalLevel);

        // Retourne juste le niveau éducatif - le front appellera InteractionService pour le message IA
        return educationalLevel;
    }

    @Override
    public List<String> provideTips(String subject, String difficulty) {
        log.info("Configuration des conseils pour la matière: {} - difficulté: {}", subject, difficulty);

        // Retourne les paramètres - le front appellera InteractionService pour les conseils IA
        return Arrays.asList(subject, difficulty);
    }

    @Override
    public String answerGeneralQuestion(String question, String context) {
        log.info("Redirection de la question vers InteractionService: {}", question);

        // Retourne un message de redirection - le front doit appeler InteractionService
        return "REDIRECT_TO_INTERACTION_SERVICE";
    }

    @Override
    public Long redirectToOtherAssistant(Long currentAssistantId, String requestType) {
        log.info("Redirection depuis l'assistant {} vers type: {}", currentAssistantId, requestType);

        // Logique simple de redirection
        return switch (requestType.toLowerCase()) {
            case "apprentissage" -> 2L; // ID assistant apprentissage
            case "recherche" -> 3L; // ID assistant recherche
            default -> currentAssistantId;
        };
    }

    @Override
    public String handleMultimediaInput(MultipartFile multimediaInput, String inputType) {
        log.info("Traitement d'un fichier multimédia de type: {}", inputType);

        // Génération d'un ID de traitement unique
        String processingId = UUID.randomUUID().toString();

        // Simulation du traitement asynchrone
        log.info("Traitement multimédia démarré avec l'ID: {}", processingId);

        return processingId;
    }
}