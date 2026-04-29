 package com.seniorstep.planner.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seniorstep.planner.domain.exception.ResourceNotFoundException;
import com.seniorstep.planner.domain.exception.ScheduleConflictException;
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
	        throw new ScheduleConflictException();
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
	
	@Transactional(readOnly = true)
	public Page<StudySlotResponse> findAll(Boolean completed, Pageable pageable) {
	    Page<StudySlot> page;
	    
	    if (completed != null) {
	        page = repository.findByCompleted(completed, pageable);
	    } else {
	        page = repository.findAll(pageable);
	    }

	    return page.map(this::toResponse);
	}
	
	@Transactional
	public StudySlotResponse complete(Long id) {
	    return repository.findById(id)
	            .map(slot -> {
	                slot.setCompleted(true);
	                return repository.save(slot);
	            })
	            .map(this::toResponse)
	            .orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	private StudySlotResponse toResponse(StudySlot slot) {
	    return new StudySlotResponse(
	            slot.getId(),
	            slot.getTitle(),
	            slot.getStartDateTime(),
	            slot.getEndDateTime(),
	            slot.isCompleted()
	    );
	}
}
