package com.task2.micro01.micro02.service;

import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.dto.UserLoginDto;
import com.task2.micro01.micro02.dto.UserTokenDto;
import com.task2.micro01.micro02.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeignClient userFeignClient;

    public List<User> getUsers() {
        return userFeignClient.getUsers();
    }

    public User getUserById(Long id) {
        return userFeignClient.getUserById(id);
    }

    public User saveUser(User user) {
        return userFeignClient.addUser(user);
    }

    public UserTokenDto authenticate(UserLoginDto userLoginDto) {
        return userFeignClient.authenticate(userLoginDto);
    }
}