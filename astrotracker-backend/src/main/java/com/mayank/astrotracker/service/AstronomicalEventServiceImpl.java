package com.mayank.astrotracker.service;

import com.mayank.astrotracker.dto.CreateEventRequest;
import com.mayank.astrotracker.dto.EventResponse;
import com.mayank.astrotracker.dto.UserResponse;
import com.mayank.astrotracker.entity.AstronomicalEvent;
import com.mayank.astrotracker.entity.User;
import com.mayank.astrotracker.repository.AstronomicalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AstronomicalEventServiceImpl implements AstronomicalEventService {

    private final AstronomicalEventRepository eventRepository;

    @Override
    public EventResponse createEvent(CreateEventRequest request) {

        AstronomicalEvent event = new AstronomicalEvent();
        event.setEventType(request.getEventType());
        event.setTitle(request.getTitle());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setVisibilityInfo(request.getVisibilityInfo());
        event.setSource(request.getSource());

        AstronomicalEvent savedEvent = eventRepository.save(event);
        return mapToResponse(savedEvent);
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EventResponse getEventById(Long id) {

        AstronomicalEvent event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return mapToResponse(event);
    }

    @Override
    public List<EventResponse> getEventsByType(String eventType) {

        return eventRepository.findByEventType(eventType).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteEvent(Long id) {

        if(!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);

    }

    private EventResponse mapToResponse(AstronomicalEvent event) {
        return EventResponse.builder()
                .id(event.getId())
                .eventType(event.getEventType())
                .title(event.getTitle())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .visibilityInfo(event.getVisibilityInfo())
                .source(event.getSource())
                .build();
    }
}
