package com.mayank.astrotracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendEmail(String email, String message) {
        logger.info("Sent email to: {}: {}", email, message);
    }

}
