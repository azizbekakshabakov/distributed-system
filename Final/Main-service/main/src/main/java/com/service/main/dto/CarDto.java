package com.service.main.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CarDto {
    private Long id;
    private String name;
    private String description;
    private Boolean enabled;
    private double tariff;

    private String fileName;
    private String username;
}
