package com.mayank.astrotracker.repository;

import com.mayank.astrotracker.entity.Subscription;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByEventTypeAndActiveTrue(String eventType);
}
