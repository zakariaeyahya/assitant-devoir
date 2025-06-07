package com.marketingconfort.assistantmanagement.controller;

import com.marketingconfort.assistantmanagement.dto.request.HomeworkAssistantRequest;
import com.marketingconfort.assistantmanagement.dto.response.HomeworkAssistantResponse;
import com.marketingconfort.assistantmanagement.service.ChildHomeworkAssistantService;
import com.marketingconfort.brainboost_common.assistant_devoir.models.ChildHomeworkAssistant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assistant-devoir")
@RequiredArgsConstructor
@Slf4j
public class HomeworkAssistantController {

    private final ChildHomeworkAssistantService assistantService;

    @PostMapping
    public ResponseEntity<HomeworkAssistantResponse> createAssistant(@Valid @RequestBody HomeworkAssistantRequest request) {
        log.info("Création d'un assistant pour l'utilisateur: {}", request.getUserId());

        ChildHomeworkAssistant assistant = convertToEntity(request);
        ChildHomeworkAssistant created = assistantService.createAssistant(assistant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(created));
    }

    @PutMapping("/{assistantId}")
    public ResponseEntity<HomeworkAssistantResponse> updateAssistant(
            @PathVariable Long assistantId,
            @Valid @RequestBody HomeworkAssistantRequest request) {

        log.info("Mise à jour de l'assistant: {}", assistantId);

        ChildHomeworkAssistant assistant = convertToEntity(request);
        ChildHomeworkAssistant updated = assistantService.updateAssistant(assistantId, assistant);

        return ResponseEntity.ok(convertToResponse(updated));
    }

    @GetMapping("/{assistantId}")
    public ResponseEntity<HomeworkAssistantResponse> getAssistant(@PathVariable Long assistantId) {
        log.info("Récupération de l'assistant: {}", assistantId);

        ChildHomeworkAssistant assistant = assistantService.getAssistantDetails(assistantId);
        return ResponseEntity.ok(convertToResponse(assistant));
    }

    @DeleteMapping("/{assistantId}")
    public ResponseEntity<Void> deleteAssistant(@PathVariable Long assistantId) {
        log.info("Suppression de l'assistant: {}", assistantId);

        assistantService.deleteAssistant(assistantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<HomeworkAssistantResponse>> listAssistants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Liste des assistants - page: {}, size: {}", page, size);

        Page<ChildHomeworkAssistant> assistants = assistantService.listAssistants(page, size);
        Page<HomeworkAssistantResponse> response = assistants.map(this::convertToResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<HomeworkAssistantResponse>> searchAssistants(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Recherche d'assistants avec le mot-clé: {}", keyword);

        Pageable pageable = PageRequest.of(page, size);
        Page<ChildHomeworkAssistant> assistants = assistantService.searchAssistants(keyword, pageable);
        Page<HomeworkAssistantResponse> response = assistants.map(this::convertToResponse);

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
    private ChildHomeworkAssistant convertToEntity(HomeworkAssistantRequest request) {
        ChildHomeworkAssistant assistant = new ChildHomeworkAssistant();
        assistant.setUserId(request.getUserId());
        assistant.setType(request.getType());
        assistant.setStatus(request.getStatus());
        assistant.setEducationLevel(request.getEducationLevel());
        assistant.setIsActive(request.getIsActive());
        assistant.setSupportedInputTypes(request.getSupportedInputTypes());
        return assistant;
    }

    private HomeworkAssistantResponse convertToResponse(ChildHomeworkAssistant assistant) {
        HomeworkAssistantResponse response = new HomeworkAssistantResponse();
        response.setId(assistant.getId());
        response.setUserId(assistant.getUserId());
        response.setType(assistant.getType());
        response.setStatus(assistant.getStatus());
        response.setEducationLevel(assistant.getEducationLevel());
        response.setIsActive(assistant.getIsActive());
        response.setSupportedInputTypes(assistant.getSupportedInputTypes());
        response.setCreatedAt(assistant.getCreatedAt());
        response.setLastUpdated(assistant.getLastUpdated());
        response.setCreatorId(assistant.getCreatorId());
        response.setLastModifierId(assistant.getLastModifierId());
        return response;
    }
}