package com.example.keycloak_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangePasswordDto {
    private String newPassword;
}
