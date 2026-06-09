package com.mayank.astrotracker.scheduler;

import com.mayank.astrotracker.entity.AstronomicalEvent;
import com.mayank.astrotracker.external.NasaApiClient;
import com.mayank.astrotracker.repository.AstronomicalEventRepository;
import com.mayank.astrotracker.service.NotificationService;
import com.mayank.astrotracker.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventScheduler {

    private final NasaApiClient nasaApiClient;
    private final SubscriptionService subscriptionService;
    private final NotificationService notificationService;
    private final AstronomicalEventRepository eventRepository;

    @Scheduled(fixedRate = 60000)
    public void fetchEvents() {
        try {
            String apiData = nasaApiClient.fetchData();
            log.info("NASA API Data: {}", apiData);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(apiData);

            String title = json.get("title").asText();
            String date = json.get("date").asText();
            String explanation = json.get("explanation").asText();

            log.info("NASA Event Title: {}", title);

            boolean exists = eventRepository.findByTitle(title).isPresent();
            if (exists) {
                log.info("Event already exists");
                return;
            }

            AstronomicalEvent event = new AstronomicalEvent();
            event.setTitle(title);
            event.setEventType("NASA_APOD");
            event.setSource("NASA");
            event.setVisibilityInfo(explanation);
            event.setStartTime(LocalDateTime.now());
            event.setEndTime(LocalDateTime.now().plusDays(1));

            AstronomicalEvent savedEvent = eventRepository.save(event);
            log.info("Saved new event with id {}", savedEvent.getId());

            String eventType = "METEOR_SHOWER";
            var subscribers = subscriptionService.getSubscribers(eventType);
            log.info("Found {} subscribers", subscribers.size());

            subscribers.forEach(sub -> notificationService.sendEmail(sub.getEmail(),
                    "Upcoming event detected: " + savedEvent.getTitle()));
        }
        catch (Exception e) {
            log.error("Failed to fetch NASA data", e);
        }
    }
}
