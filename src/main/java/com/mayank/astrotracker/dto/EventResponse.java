package com.mayank.astrotracker.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EventResponse {
    private long id;

    private String eventType;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String visibilityInfo;

    private String source;
}
