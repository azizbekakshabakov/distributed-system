package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.UserLoginDto;
import com.task2.micro01.micro02.dto.UserTokenDto;
import com.task2.micro01.micro02.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/token")
    public UserTokenDto signIn(@RequestBody UserLoginDto userLoginDto) {
        return userService.authenticate(userLoginDto);
    }
}
