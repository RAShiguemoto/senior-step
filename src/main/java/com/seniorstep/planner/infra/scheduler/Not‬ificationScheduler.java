package com.seniorstep.planner.infra.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seniorstep.planner.domain.model.NotificationType;
import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.repository.StudySlotRepository;
import com.seniorstep.planner.domain.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Not‬ificationScheduler {

	private final StudySlotRepository repository;
	private final NotificationService notificationService;
	
	@Scheduled(fixedDelay = 60000)
    @Transactional
	public void checkAndNotify() {
        LocalDateTime windowStart = LocalDateTime.now();
        LocalDateTime windowEnd = windowStart.plusMinutes(20);

        List<StudySlot> pendingSlots = repository.findByStartDateTimeBeforeAndNotificationSentFalse(windowEnd);

        log.info("Checking for pending notifications. Found: {}", pendingSlots.size());

        pendingSlots.forEach(slot -> {
            notificationService.notify(slot, NotificationType.TELEGRAM);
            slot.setNotificationSent(true);
            repository.save(slot);
        });
    }
}
