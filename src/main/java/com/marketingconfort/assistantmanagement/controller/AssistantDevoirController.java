package com.marketingconfort.assistantmanagement.controller;

import com.marketingconfort.assistantmanagement.dto.request.AssistantDevoirRequest;
import com.marketingconfort.assistantmanagement.dto.response.AssistantDevoirResponse;
import com.marketingconfort.assistantmanagement.service.AssistantDevoirEnfantService;
import com.marketingconfort.brainboost_common.assistant_devoir.models.AssistantDevoirEnfant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assistant-devoir")
@RequiredArgsConstructor
@Slf4j
public class AssistantDevoirController {

    private final AssistantDevoirEnfantService assistantService;

    @PostMapping
    public ResponseEntity<AssistantDevoirResponse> createAssistant(@Valid @RequestBody AssistantDevoirRequest request) {
        log.info("Création d'un assistant pour l'utilisateur: {}", request.getUserId());

        AssistantDevoirEnfant assistant = convertToEntity(request);
        AssistantDevoirEnfant created = assistantService.createAssistant(assistant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(created));
    }

    @PutMapping("/{assistantId}")
    public ResponseEntity<AssistantDevoirResponse> updateAssistant(
            @PathVariable Long assistantId,
            @Valid @RequestBody AssistantDevoirRequest request) {

        log.info("Mise à jour de l'assistant: {}", assistantId);

        AssistantDevoirEnfant assistant = convertToEntity(request);
        AssistantDevoirEnfant updated = assistantService.updateAssistant(assistantId, assistant);

        return ResponseEntity.ok(convertToResponse(updated));
    }

    @GetMapping("/{assistantId}")
    public ResponseEntity<AssistantDevoirResponse> getAssistant(@PathVariable Long assistantId) {
        log.info("Récupération de l'assistant: {}", assistantId);

        AssistantDevoirEnfant assistant = assistantService.getAssistantDetails(assistantId);
        return ResponseEntity.ok(convertToResponse(assistant));
    }

    @DeleteMapping("/{assistantId}")
    public ResponseEntity<Void> deleteAssistant(@PathVariable Long assistantId) {
        log.info("Suppression de l'assistant: {}", assistantId);

        assistantService.deleteAssistant(assistantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AssistantDevoirResponse>> listAssistants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Liste des assistants - page: {}, size: {}", page, size);

        Page<AssistantDevoirEnfant> assistants = assistantService.listAssistants(page, size);
        Page<AssistantDevoirResponse> response = assistants.map(this::convertToResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AssistantDevoirResponse>> searchAssistants(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Recherche d'assistants avec le mot-clé: {}", keyword);

        Pageable pageable = PageRequest.of(page, size);
        Page<AssistantDevoirEnfant> assistants = assistantService.searchAssistants(keyword, pageable);
        Page<AssistantDevoirResponse> response = assistants.map(this::convertToResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/greet")
    public ResponseEntity<String> greetUser(
            @RequestParam Long userId,
            @RequestParam String educationalLevel) {

        log.info("Message d'accueil pour l'utilisateur: {} - niveau: {}", userId, educationalLevel);

        String greeting = assistantService.greetUser(userId, educationalLevel);
        return ResponseEntity.ok(greeting);
    }

    @PostMapping("/tips")
    public ResponseEntity<List<String>> provideTips(
            @RequestParam String subject,
            @RequestParam String difficulty) {

        log.info("Conseils pour la matière: {} - difficulté: {}", subject, difficulty);

        List<String> tips = assistantService.provideTips(subject, difficulty);
        return ResponseEntity.ok(tips);
    }

    @PostMapping("/multimedia")
    public ResponseEntity<String> handleMultimedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam String inputType) {

        log.info("Traitement multimédia - type: {}", inputType);

        String processingId = assistantService.handleMultimediaInput(file, inputType);
        return ResponseEntity.accepted().body(processingId);
    }

    // Méthodes de conversion
    private AssistantDevoirEnfant convertToEntity(AssistantDevoirRequest request) {
        AssistantDevoirEnfant assistant = new AssistantDevoirEnfant();
        assistant.setUserId(request.getUserId());
        assistant.setType(request.getType());
        assistant.setStatus(request.getStatus());
        assistant.setEducationLevel(request.getEducationLevel());
        assistant.setEnabled(request.getEnabled());
        assistant.setSupportedInputTypes(request.getSupportedInputTypes());
        return assistant;
    }

    private AssistantDevoirResponse convertToResponse(AssistantDevoirEnfant assistant) {
        AssistantDevoirResponse response = new AssistantDevoirResponse();
        response.setId(assistant.getId());
        response.setUserId(assistant.getUserId());
        response.setType(assistant.getType());
        response.setStatus(assistant.getStatus());
        response.setEducationLevel(assistant.getEducationLevel());
        response.setEnabled(assistant.getEnabled());
        response.setSupportedInputTypes(assistant.getSupportedInputTypes());
        response.setCreatedAt(assistant.getCreatedAt());
        response.setLastUpdated(assistant.getLastUpdated());
        response.setCreatorId(assistant.getCreatorId());
        response.setLastModifierId(assistant.getLastModifierId());
        return response;
    }
}