package com.mayank.astrotracker.scheduler;

import com.mayank.astrotracker.external.NasaApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventScheduler {

    private final NasaApiClient nasaApiClient;

    @Scheduled(fixedRate = 6000)
    public void fetchEvents() {
        String apiData = nasaApiClient.fetchData();
        log.info("NASA API Data: {}", apiData);
    }
}
