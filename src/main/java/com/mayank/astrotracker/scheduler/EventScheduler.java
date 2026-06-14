package com.mayank.astrotracker.scheduler;

import com.mayank.astrotracker.dto.EventResponse;
import com.mayank.astrotracker.dto.LiveEventMessage;
import com.mayank.astrotracker.entity.AstronomicalEvent;
import com.mayank.astrotracker.external.NasaApiClient;
import com.mayank.astrotracker.repository.AstronomicalEventRepository;
import com.mayank.astrotracker.service.EventPublisherService;
import com.mayank.astrotracker.service.NotificationService;
import com.mayank.astrotracker.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventScheduler {

    private final NasaApiClient nasaApiClient;
    private final SubscriptionService subscriptionService;
    private final NotificationService notificationService;
    private final AstronomicalEventRepository eventRepository;
    private final EventPublisherService eventPublisherService;

    @Scheduled(fixedRate = 60000)
    public void fetchEvents() {
        try {
            String apiData = nasaApiClient.fetchAsteroidEvents();
            log.info("NASA API Data: {}", apiData);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(apiData);
            log.info("NASA Response: {}", json.toPrettyString());

            JsonNode nearEarthObjects = json.get("near_earth_objects");
            JsonNode  todayEvents = nearEarthObjects.elements().next();

            for (JsonNode todayEvent : todayEvents) {
                String name = todayEvent.get("name").asText();
                JsonNode approachData = todayEvent.get("close_approach_data").get(0);
                String date = approachData.get("close_approach_date").asText();
                String distance = approachData.get("miss_distance").get("kilometers").asText();

                log.info("NASA Event Title: {}", name);

                boolean exists = eventRepository.findByTitle(name).isPresent();
                if (exists) {
                    log.info("Event already exists");
                    continue;
                }

                AstronomicalEvent event = new AstronomicalEvent();
                event.setTitle(name);
                event.setEventType("ASTEROID_CLOSE_APPROACH");
                event.setSource("NASA");
                event.setVisibilityInfo("Missed distance: " + distance + " km");
                event.setStartTime(LocalDate.parse(date).atStartOfDay());
                event.setEndTime(LocalDate.parse(date).atStartOfDay().plusHours(1));

                AstronomicalEvent savedEvent = eventRepository.save(event);
                log.info("Saved new event with id {}", savedEvent.getId());

                LiveEventMessage eventMessage = LiveEventMessage.builder()
                        .id(savedEvent.getId())
                        .eventType(savedEvent.getEventType())
                        .title(savedEvent.getTitle())
                        .source(savedEvent.getSource())
                        .visibilityInfo(savedEvent.getVisibilityInfo())
                        .detectedAt(LocalDateTime.now())
                        .build();

                eventPublisherService.publishEvent(eventMessage);


                String eventType = "ASTEROID_CLOSE_APPROACH";
                var subscribers = subscriptionService.getSubscribers(eventType);
                log.info("Found {} subscribers", subscribers.size());

                subscribers.forEach(sub -> notificationService.sendEmail(sub.getEmail(),
                        "Upcoming event detected: " + savedEvent.getTitle()));

            }
        }
        catch (Exception e) {
            log.error("Failed to fetch NASA data", e);
        }
    }
}
