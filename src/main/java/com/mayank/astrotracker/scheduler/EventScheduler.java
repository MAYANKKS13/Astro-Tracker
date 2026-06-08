package com.mayank.astrotracker.scheduler;

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

            boolean exists = eventRepository.findByTitle(title).isPresent();
            if (exists) {
                log.info("Event already exists");
                return;
            }

            var subscribers = subscriptionService.getSubscribers(eventType);
            log.info("Found {} subscribers", subscribers.size());

            subscribers.forEach(sub -> notificationService.sendEmail(sub.getEmail(),
                    "Upcoming event detected: " + eventType));
        }
        catch (Exception e) {
            log.error("Failed to fetch NASA data", e);
        }
    }
}
