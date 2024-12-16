package com.example.keycloak_service.api;

import com.example.keycloak_service.dto.UserLoginDto;
import com.example.keycloak_service.dto.UserTokenDto;
import com.example.keycloak_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

//    @GetMapping
//    public String login() {
//        return "this is login page";
//    }

    @PostMapping(value = "/token")
    public UserTokenDto signIn(@RequestBody UserLoginDto userLoginDto) {
        return userService.authenticate(userLoginDto);
    }
}
