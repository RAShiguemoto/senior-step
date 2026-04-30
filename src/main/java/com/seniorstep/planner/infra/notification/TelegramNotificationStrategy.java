package com.seniorstep.planner.infra.notification;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seniorstep.planner.domain.model.NotificationType;
import com.seniorstep.planner.domain.model.StudySlot;
import com.seniorstep.planner.domain.service.NotificationStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TelegramNotificationStrategy implements NotificationStrategy {
	
	private final RestClient restClient;
	
	@Value("${api.telegram.token}")
	private String botToken;
	
	@Value("${api.telegram.chat-id}")
	private String chatId;
	
	public TelegramNotificationStrategy() {
		this.restClient = RestClient.create();
	}

	@Override
	public void send(StudySlot slot) {
		String message = "📖 *Time to Study!*\n\n" +
                "*Topic:* " + slot.getTitle() + "\n" +
                "*Schedule:* " + slot.getStartDateTime().toLocalTime();
		
		try {
			restClient.post()
			.uri("https://api.telegram.org/bot" + botToken + "/sendMessage")
			.body(Map.of("chat_id", chatId, "text", message, "parse_mode", "Markdown"))
			.retrieve()
			.toBodilessEntity();
			
			log.info("Telegram notification sent for slot: {}", slot.getId());
		} catch (Exception e) {
			log.error("Failed to send Telegram notification: {}", e.getMessage());
		}
	}

	@Override
	public boolean supports(NotificationType type) {
		return NotificationType.TELEGRAM.equals(type);
	}
	
}
