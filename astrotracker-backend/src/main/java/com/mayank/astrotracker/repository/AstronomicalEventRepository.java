package com.mayank.astrotracker.repository;

import com.mayank.astrotracker.entity.AstronomicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AstronomicalEventRepository extends JpaRepository<AstronomicalEvent, Long> {

    Optional<AstronomicalEvent> findByTitle(String title);

    List<AstronomicalEvent> findByEventType(String eventType);
}
