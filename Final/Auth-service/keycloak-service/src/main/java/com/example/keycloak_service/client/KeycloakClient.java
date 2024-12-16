package com.example.keycloak_service.client;

import com.example.keycloak_service.dto.UserCreateDto;
import com.example.keycloak_service.dto.UserLoginDto;
import com.example.keycloak_service.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakClient {
    private final Keycloak keycloak;
    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public UserRepresentation createUser(UserCreateDto userCreateDto) {
        UserRepresentation newUser = new UserRepresentation();
        newUser.setEmail(userCreateDto.getEmail());
        newUser.setEmailVerified(true);
        newUser.setUsername(userCreateDto.getUsername());
        newUser.setFirstName(userCreateDto.getFirstName());
        newUser.setLastName(userCreateDto.getLastName());
        newUser.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(userCreateDto.getPassword());
        credentials.setTemporary(false);

        newUser.setCredentials(List.of(credentials));

        Response response = keycloak
                .realm(realm)
                .users()
                .create(newUser);

        if (response.getStatus() != HttpStatus.CREATED.value()) {
            log.error("Error creating user: {}", response.getStatus());
            throw new RuntimeException("Error creating user: " + response.getStatus());
        }

        List<UserRepresentation> userList = keycloak.realm(realm)
                .users()
                .search(userCreateDto.getUsername());

        if (userList.isEmpty()) {
            throw new RuntimeException("User not found after creation");
        }

        UserRepresentation createdUser = userList.get(0);

        // достать id
        String userId = createdUser.getId();

        // достать role representation
        RoleRepresentation role = keycloak.realm(realm)
                .roles()
                .get("MODERATOR") // Replace with your role name
                .toRepresentation();

        // добавить роль к юзеру
        keycloak.realm(realm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(role));

        return createdUser;
    }

    public UserTokenDto signIn(UserLoginDto userLoginDto) {
        String tokenEndpoint = url + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password"); //client_credentials
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", userLoginDto.getUsername());
        formData.add("password", userLoginDto.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<Map> response
                = restTemplate.postForEntity(tokenEndpoint, new HttpEntity<>(formData, headers), Map.class);

        Map<String, Object> responseBody = response.getBody();

        if (! response.getStatusCode().is2xxSuccessful() || responseBody == null) {
            log.error("Error signIn: {}", userLoginDto.getUsername());
            throw new RuntimeException("Error signIn: " + response.getStatusCode());
        }

        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setToken((String) responseBody.get("access_token"));
        userTokenDto.setRefreshToken((String) responseBody.get("refresh_token"));

        return userTokenDto;
    }

    public void changePassword(String username, String newPassword) {
        List<UserRepresentation> users =
                keycloak.realm(realm)
                        .users()
                        .search(username);

        if (users.isEmpty()){
            log.error("User not found: {}", username);
            throw new RuntimeException("User not found: " + username);
        }

        UserRepresentation userRepresentation = users.get(0);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(newPassword);
        credentials.setTemporary(false);

        keycloak.realm(realm)
                .users()
                .get(userRepresentation.getId())
                .resetPassword(credentials);

        log.info("Password changed: {}", username);
    }
}
