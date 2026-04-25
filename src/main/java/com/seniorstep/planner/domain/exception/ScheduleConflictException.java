package com.seniorstep.planner.domain.exception;

import java.time.LocalDateTime;

public class ScheduleConflictException extends BusinessException {
    public ScheduleConflictException() {
        super("There is already a study slot scheduled for this period.");
    }

    public ScheduleConflictException(LocalDateTime start, LocalDateTime end) {
        super(String.format("Conflict detected for the period: %s to %s", start, end));
    }
}
