package com.seniorstep.planner.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.repository.StudySlotRepository;

public class StudySlotService {

	private final StudySlotRepository repository;

	public StudySlotService(StudySlotRepository repository) {
        this.repository = repository;
    }
	
	@Transactional
    public StudySlot create(StudySlot slot) {
        if (slot.getEndDateTime().isBefore(slot.getStartDateTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        if (repository.existsOverlapping(slot.getStartDateTime(), slot.getEndDateTime())) {
            throw new RuntimeException("Schedule conflict: There is already a study slot at this time");
        }

        return repository.save(slot);
    }
}
