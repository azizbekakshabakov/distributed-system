package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public User getItemById(@PathVariable(name = "id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User createItem(@RequestBody User user){
        return userService.saveUser(user);
    }
}
