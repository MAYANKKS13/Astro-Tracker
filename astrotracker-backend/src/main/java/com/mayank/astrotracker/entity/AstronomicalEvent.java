package com.mayank.astrotracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "astronomical_events")
@Getter
@Setter
public class AstronomicalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String visibilityInfo;

    private String source;
}
