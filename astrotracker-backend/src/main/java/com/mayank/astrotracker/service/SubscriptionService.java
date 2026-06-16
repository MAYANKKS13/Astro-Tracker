package com.mayank.astrotracker.service;

import com.mayank.astrotracker.entity.Subscription;
import com.mayank.astrotracker.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public Subscription subscribe(String email, String eventType) {
        Optional<Subscription> existing = repository.findByEmailAndEventType(email, eventType);
        if (existing.isPresent()) {
            throw new RuntimeException("Already subscribed to this event type");
        }
        Subscription subscription = new Subscription(email, eventType);
        return repository.save(subscription);
    }

    public List<Subscription> getSubscribers(String eventType) {
        return repository.findByEventTypeAndActiveTrue(eventType);
    }


    public void unsubscribe(Long id) {
            Subscription subscription = repository.findById(id).orElseThrow(() -> new RuntimeException("Subscription not found"));
            subscription.setActive(false);
            repository.save(subscription);
    }


//    public void unsubscribe(String email, String eventType) {
//        List<Subscription> subscriptions = repository.findByEventTypeAndActiveTrue(eventType);
//        for (Subscription sub : subscriptions) {
//            if (sub.getEmail().equals(email)) {
//                sub.setActive(false);
//                repository.save(sub);
//            }
//        }
//    }
}
