package com.marketingconfort.assistantmanagement.service.impl;

import com.marketingconfort.assistantmanagement.repository.ChildHomeworkAssistantRepository;
import com.marketingconfort.brainboost_common.assistant_devoir.models.ChildHomeworkAssistant;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.AssistantStatus;
import com.marketingconfort.brainboost_common.assistant_devoir.enums.EducationLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChildHomeworkAssistantServiceTest {

    @Mock
    private ChildHomeworkAssistantRepository assistantRepository;

    @InjectMocks
    private ChildHomeworkAssistantServiceImpl assistantService;

    @Test
    void createAssistant_Success() {
        // Given
        ChildHomeworkAssistant assistant = new ChildHomeworkAssistant();
        assistant.setUserId(1L);
        assistant.setType("DEVOIR");
        assistant.setEducationLevel(EducationLevel.CP);

        ChildHomeworkAssistant savedAssistant = new ChildHomeworkAssistant();
        savedAssistant.setId(1L);
        savedAssistant.setUserId(1L);
        savedAssistant.setStatus(AssistantStatus.ACTIVE);

        when(assistantRepository.save(any())).thenReturn(savedAssistant);

        // When
        ChildHomeworkAssistant result = assistantService.createAssistant(assistant);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(AssistantStatus.ACTIVE, result.getStatus());
        verify(assistantRepository).save(any());
    }

    @Test
    void getAssistantDetails_Success() {
        // Given
        Long assistantId = 1L;
        ChildHomeworkAssistant assistant = new ChildHomeworkAssistant();
        assistant.setId(assistantId);
        assistant.setUserId(1L);

        when(assistantRepository.findById(assistantId)).thenReturn(Optional.of(assistant));

        // When
        ChildHomeworkAssistant result = assistantService.getAssistantDetails(assistantId);

        // Then
        assertNotNull(result);
        assertEquals(assistantId, result.getId());
    }

    @Test
    void getAssistantDetails_NotFound() {
        // Given
        Long assistantId = 999L;
        when(assistantRepository.findById(assistantId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> assistantService.getAssistantDetails(assistantId));

        assertTrue(exception.getMessage().contains("Assistant non trouvé"));
    }

    @Test
    void listAssistants_Success() {
        // Given
        ChildHomeworkAssistant assistant1 = new ChildHomeworkAssistant();
        assistant1.setId(1L);
        ChildHomeworkAssistant assistant2 = new ChildHomeworkAssistant();
        assistant2.setId(2L);

        Page<ChildHomeworkAssistant> page = new PageImpl<>(Arrays.asList(assistant1, assistant2));
        when(assistantRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // When
        Page<ChildHomeworkAssistant> result = assistantService.listAssistants(0, 10);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }

    @Test
    void greetUser_Success() {
        // Given
        Long userId = 1L;
        String educationalLevel = "CP";

        // When
        String result = assistantService.greetUser(userId, educationalLevel);

        // Then
        assertEquals("CP", result);
    }
}