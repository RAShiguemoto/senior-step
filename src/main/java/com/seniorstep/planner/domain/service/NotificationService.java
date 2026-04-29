package com.seniorstep.planner.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seniorstep.planner.domain.model.NotificationType;
import com.seniorstep.planner.domain.model.StudySlot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final List<NotificationStrategy> strategies;
	
	public void notify(StudySlot slot, NotificationType type) {
		strategies.stream()
		.filter(s -> s.supports(type))
		.findFirst()
		.ifPresent(s -> s.send(slot));
	}
}
