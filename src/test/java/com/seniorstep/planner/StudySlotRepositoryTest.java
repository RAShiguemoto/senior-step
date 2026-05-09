package com.seniorstep.planner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.repository.StudySlotRepository;

public class StudySlotRepositoryTest extends AbstractIntegrationTest {

	@Autowired
    private StudySlotRepository repository;

    @Test
    void should_ReturnTrue_WhenOverlapExists() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        StudySlot existing = StudySlot.builder()
                .title("Existing")
                .startDateTime(start)
                .endDateTime(start.plusHours(1))
                .build();
        repository.save(existing);

        boolean exists = repository.existsOverlapping(start.minusMinutes(30), start.plusMinutes(30));

        assertThat(exists).isTrue();
    }
}
