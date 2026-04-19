package com.seniorstep.planner.infra.controller.dto;

import java.time.LocalDateTime;

public record StudySlotResponse(
	    Long id,
	    String title,
	    LocalDateTime startDateTime,
	    LocalDateTime endDateTime,
	    boolean completed
) {}