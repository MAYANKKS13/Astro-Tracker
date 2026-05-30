package com.mayank.astrotracker.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NasaApiClient {
    private final RestTemplate restTemplate;

    public String fetchData() {
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
        return restTemplate.getForObject(apiUrl, String.class);
    }

}
