package com.mayank.astrotracker.service;

import com.mayank.astrotracker.dto.EventResponse;
import com.mayank.astrotracker.dto.LiveEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventPublisherService {

    private final SimpMessagingTemplate messagingTemplate;

    public void publishEvent(LiveEventMessage eventMessage) {
        log.info("Publishing websocket event: {}", eventMessage);

        messagingTemplate.convertAndSend("/topic/events", eventMessage);
    }

}
