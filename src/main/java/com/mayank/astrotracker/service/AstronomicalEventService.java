package com.mayank.astrotracker.service;

import com.mayank.astrotracker.dto.CreateEventRequest;
import com.mayank.astrotracker.dto.EventResponse;

import java.util.List;

public interface AstronomicalEventService {
    EventResponse createEvent(CreateEventRequest request);

    List<EventResponse> getAllEvents();

}
