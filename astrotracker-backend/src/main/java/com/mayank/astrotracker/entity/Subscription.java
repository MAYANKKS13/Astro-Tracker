package com.mayank.astrotracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String eventType;

    private boolean active = true;

    public Subscription() {}

    public Subscription(String email, String eventType) {
        this.email = email;
        this.eventType = eventType;
    }

}
