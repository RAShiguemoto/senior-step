package com.seniorstep.planner.domain.exception;

public class ResourceNotFoundException extends BusinessException {

	public ResourceNotFoundException(Long id) {
		super(String.format("Resource with ID %d not found", id));
	}

}
