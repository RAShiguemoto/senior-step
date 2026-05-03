package com.seniorstep.planner.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seniorstep.planner.domain.exception.ScheduleConflictException;
import com.seniorstep.planner.domain.repository.StudySlotRepository;
import com.seniorstep.planner.infra.controller.dto.StudySlotRequest;

@ExtendWith(MockitoExtension.class)
public class StudySlotServiceTest {

	@Mock
    private StudySlotRepository repository;
    
	@Mock
    private NotificationService notificationService;
	
	@InjectMocks
    private StudySlotService service;
	
	@Test
    @DisplayName("Should throw ScheduleConflictException when overlap exists")
    void create_ShouldThrowException_WhenOverlapExists() {
        var request = new StudySlotRequest("Java 25", 
            LocalDateTime.now().plusHours(1), 
            LocalDateTime.now().plusHours(2));

        when(repository.existsOverlapping(any(), any())).thenReturn(true);

        assertThrows(ScheduleConflictException.class, () -> {
            service.create(request);
        });

        verify(repository, never()).save(any());
    }
}
