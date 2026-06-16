package com.mayank.astrotracker.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class NasaApiClient {
    private final RestTemplate restTemplate;

    @Value("${nasa.api.key}")
    private String apiKey;

    public String fetchAsteroidEvents() {
        LocalDate today = LocalDate.now();
        String url = "https://api.nasa.gov/neo/rest/v1/feed" + "?start_date=" + today + "&end_date=" + today + "&api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String fetchData() {
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key="+ apiKey;
        return restTemplate.getForObject(apiUrl, String.class);
    }

}
