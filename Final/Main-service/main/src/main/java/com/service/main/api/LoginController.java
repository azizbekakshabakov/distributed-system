package com.service.main.api;

import com.service.main.dto.UserLoginDto;
import com.service.main.dto.UserTokenDto;
import com.service.main.service.UserService;
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
