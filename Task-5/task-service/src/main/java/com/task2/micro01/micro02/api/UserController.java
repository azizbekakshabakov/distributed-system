package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.dto.UserChangePasswordDto;
import com.task2.micro01.micro02.dto.UserCreateDto;
import com.task2.micro01.micro02.dto.UserDto;
import com.task2.micro01.micro02.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

//    @GetMapping
//    public List<User> getUsers(){
//        return userService.getUsers();
//    }
//
//    @GetMapping(value = "/{id}")
//    public User getItemById(@PathVariable(name = "id") Long id){
//        return userService.getUserById(id);
//    }
//
//    @PostMapping
//    public User createItem(@RequestBody User user){
//        return userService.saveUser(user);
//    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.createUser(userCreateDto), HttpStatus.OK);
    }

    @GetMapping(value = "/current-user-name")
    public String getCurrentUserName(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return userService.getCurrentUserName(authHeader);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @GetMapping(value = "/current-user")
    public UserDto getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return userService.getCurrentUser(authHeader);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return userService.changePassword(userChangePasswordDto, authHeader);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }
}
