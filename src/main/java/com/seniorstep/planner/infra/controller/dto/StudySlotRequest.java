package com.seniorstep.planner.infra.controller.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudySlotRequest(
	@NotBlank String title,
	@NotNull @Future LocalDateTime startDateTime,
	@NotNull LocalDateTime endDateTime
) {
	public StudySlotRequest {
        if (endDateTime != null && startDateTime != null && endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}