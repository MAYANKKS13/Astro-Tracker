package com.mayank.astrotracker.controller;

import com.mayank.astrotracker.dto.CreateSubscription;
import com.mayank.astrotracker.dto.EventResponse;
import com.mayank.astrotracker.entity.Subscription;
import com.mayank.astrotracker.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public Subscription subscribe(@RequestBody CreateSubscription subscriptionReq) {
        return service.subscribe(subscriptionReq.getEmail(), subscriptionReq.getEventType());
    }

    @GetMapping("/event/{eventType}")
    public List<Subscription> getSubscribersByEventType(@PathVariable String eventType) {
        return service.getSubscribers(eventType);
    }

    @DeleteMapping("/{id}")
    public void unsubscribe(@PathVariable Long id) {
        service.unsubscribe(id);
    }

}
