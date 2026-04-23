package com.seniorstep.planner.domain.exception;

public abstract class BusinessException extends RuntimeException {
	protected BusinessException(String message) {
        super(message);
    }
}
