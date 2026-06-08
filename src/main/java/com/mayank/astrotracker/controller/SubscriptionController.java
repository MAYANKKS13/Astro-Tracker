package com.mayank.astrotracker.controller;

import com.mayank.astrotracker.entity.Subscription;
import com.mayank.astrotracker.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping
    public Subscription subscribe(@RequestParam String email, @RequestParam String eventType) {
        return service.subscribe(email, eventType);
    }

}
