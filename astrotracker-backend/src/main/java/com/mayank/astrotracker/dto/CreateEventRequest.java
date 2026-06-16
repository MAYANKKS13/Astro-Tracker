package com.mayank.astrotracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventRequest {
    private String eventType;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String visibilityInfo;

    private String source;
}
