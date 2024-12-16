package com.example.keycloak_service.service;

import com.example.keycloak_service.client.KeycloakClient;
import com.example.keycloak_service.dto.UserCreateDto;
import com.example.keycloak_service.dto.UserDto;
import com.example.keycloak_service.dto.UserLoginDto;
import com.example.keycloak_service.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakClient keycloakClient;

    public UserDto createUser(UserCreateDto userCreateDto) {
        UserRepresentation userRepresentation = keycloakClient.createUser(userCreateDto);
        UserDto userDto = new UserDto();
        userDto.setUsername(userRepresentation.getUsername());
        userDto.setEmail(userRepresentation.getEmail());
        userDto.setFirstName(userRepresentation.getFirstName());
        userDto.setLastName(userRepresentation.getLastName());

        return userDto;
    }

    public UserTokenDto authenticate(UserLoginDto userLoginDto) {
        return keycloakClient.signIn(userLoginDto);
    }

    public void changePassword(String userName, String password) {
        keycloakClient.changePassword(userName, password);
    }
}
