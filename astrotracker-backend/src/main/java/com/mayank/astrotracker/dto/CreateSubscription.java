package com.mayank.astrotracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubscription {
    private String email;
    private String eventType;
}
