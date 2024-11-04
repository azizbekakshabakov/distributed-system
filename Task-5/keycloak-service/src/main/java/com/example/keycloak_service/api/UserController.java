package com.example.keycloak_service.api;

import com.example.keycloak_service.converter.UserUtils;
import com.example.keycloak_service.dto.UserChangePasswordDto;
import com.example.keycloak_service.dto.UserCreateDto;
import com.example.keycloak_service.dto.UserDto;
import com.example.keycloak_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.createUser(userCreateDto), HttpStatus.OK);
    }

    @GetMapping(value = "/current-user-name")
    public String getCurrentUserName() {
//        System.out.println(request.getHeader("Authorization"));
        return UserUtils.getCurrentUserName();
    }

    @GetMapping(value = "/current-user")
    public UserDto getCurrentUser() {
        return UserUtils.getCurrentUser();
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto) {
        String userName = UserUtils.getCurrentUserName();
        if (userName == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not find current user");
        }

        try {
            userService.changePassword(userName, userChangePasswordDto.getNewPassword());

            return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error changing password");
        }
    }
}
