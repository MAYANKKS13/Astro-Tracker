package com.mayank.astrotracker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class LiveEventMessage {
    private Long id;

    private String eventType;

    private String title;

    private String source;

    private String visibilityInfo;

    private LocalDateTime detectedAt;
}
