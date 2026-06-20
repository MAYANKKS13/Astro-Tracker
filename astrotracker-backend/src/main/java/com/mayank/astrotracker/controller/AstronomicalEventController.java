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

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/type/{eventType}")
    public List<EventResponse> getEventsByType(@PathVariable String eventType) {
        return eventService.getEventsByType(eventType);
    }

    @GetMapping("/latest")
    public List<EventResponse> getLatestEvents(@RequestParam(defaultValue = "20") int limit) {
        return eventService.getLatestEvents(limit);
    }

    @DeleteMapping("/{id}")
    public String deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }


}
