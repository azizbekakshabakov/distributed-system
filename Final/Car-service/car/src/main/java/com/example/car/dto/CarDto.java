package com.example.car.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class CarDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private boolean enabled;
    private double tariff;

    private String fileName;
    private String username;
}
