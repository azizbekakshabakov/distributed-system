package com.example.car.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarDto {
    private Long id;
    private String name;
    private String description;
    private boolean enabled;
    private double tariff;

    private String fileName;
    private String mimeType;
    private String username;
}
