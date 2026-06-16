package com.seniorstep.planner.infra.controller.dto;

import com.seniorstep.planner.domain.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
	    @NotBlank @Email String email,
	    @NotBlank String password,
	    @NotNull Role role
) {}
