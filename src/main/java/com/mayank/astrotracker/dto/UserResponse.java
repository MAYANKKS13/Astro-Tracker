package com.mayank.astrotracker.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private Double latitude;

    private Double longitude;

    private String timezone;

}
