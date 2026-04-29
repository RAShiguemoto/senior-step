package com.seniorstep.planner.domain.service;

import com.seniorstep.planner.domain.model.NotificationType;
import com.seniorstep.planner.domain.model.StudySlot;

public interface NotificationStrategy {

	void send(StudySlot slot);
	
	boolean supports(NotificationType type);
}
