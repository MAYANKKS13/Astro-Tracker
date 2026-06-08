package com.mayank.astrotracker.service;

import com.mayank.astrotracker.entity.Subscription;
import com.mayank.astrotracker.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public Subscription subscribe(String email, String eventType) {
        Subscription subscription = new Subscription(email, eventType);
        return repository.save(subscription);
    }

    public List<Subscription> getSubscribers(String eventType) {
        return repository.findByEventTypeAndActiveTrue(eventType);
    }
}
