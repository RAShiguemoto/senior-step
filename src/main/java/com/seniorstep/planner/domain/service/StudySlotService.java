package com.seniorstep.planner.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.repository.StudySlotRepository;
import com.seniorstep.planner.infra.controller.dto.StudySlotRequest;
import com.seniorstep.planner.infra.controller.dto.StudySlotResponse;

@Service
public class StudySlotService {

	private final StudySlotRepository repository;

	public StudySlotService(StudySlotRepository repository) {
        this.repository = repository;
    }
	
	@Transactional
    public StudySlotResponse  create(StudySlotRequest request) {
		if (repository.existsOverlapping(request.startDateTime(), request.endDateTime())) {
	        throw new RuntimeException("Schedule conflict!");
	    }
				
		StudySlot entity = StudySlot.builder()
	            .title(request.title())
	            .startDateTime(request.startDateTime())
	            .endDateTime(request.endDateTime())
	            .completed(false)
	            .build();
		
		StudySlot saved = repository.save(entity);

        return new StudySlotResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getStartDateTime(),
                saved.getEndDateTime(),
                saved.isCompleted()
        );
    }
}
