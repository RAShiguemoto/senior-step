package com.seniorstep.planner.domain.exception;

public class ScheduleConflictException extends BusinessException {
	public ScheduleConflictException(String message) {
        super(message);
    }
}
