package com.seniorstep.planner.infra.notification;

import org.springframework.stereotype.Component;

import com.seniorstep.planner.domain.model.NotificationType;
import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.service.NotificationStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogNotificationStrategy implements NotificationStrategy {@Override
	public void send(StudySlot slot) {
		log.info("🔔 [NOTIFICATION LOG]: It's time to study! Topic: {} | Period: {} - {}", 
				slot.getTitle(),
				slot.getStartDateTime(), 
				slot.getEndDateTime());
	}

	@Override
	public boolean supports(NotificationType type) {
		return NotificationType.LOG.equals(type);
	}

}
