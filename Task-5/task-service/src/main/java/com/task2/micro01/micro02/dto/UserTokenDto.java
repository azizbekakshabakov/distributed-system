package com.task2.micro01.micro02.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDto {
    private String token;
    private String refreshToken;
}
