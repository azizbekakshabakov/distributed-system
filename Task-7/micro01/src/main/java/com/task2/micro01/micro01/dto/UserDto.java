package com.task2.micro01.micro01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String fullName;
}
