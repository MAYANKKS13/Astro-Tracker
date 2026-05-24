package com.mayank.astrotracker.repository;

import com.mayank.astrotracker.entity.AstronomicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronomicalEventRepository extends JpaRepository<AstronomicalEvent, Long> {
}
