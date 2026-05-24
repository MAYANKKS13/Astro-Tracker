package com.mayank.astrotracker.controller;

import com.mayank.astrotracker.dto.CreateEventRequest;
import com.mayank.astrotracker.dto.CreateUserRequest;
import com.mayank.astrotracker.dto.EventResponse;
import com.mayank.astrotracker.dto.UserResponse;
import com.mayank.astrotracker.service.AstronomicalEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class AstronomicalEventController {
    private final AstronomicalEventService eventService;

    @PostMapping
    public EventResponse createEvent(@RequestBody CreateEventRequest request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

}
