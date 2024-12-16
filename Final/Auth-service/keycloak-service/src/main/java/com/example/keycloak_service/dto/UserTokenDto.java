package com.example.keycloak_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDto {
    private String token;
    private String refreshToken;
}
