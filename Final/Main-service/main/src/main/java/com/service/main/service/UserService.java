package com.service.main.service;

import com.service.main.dto.*;
import com.service.main.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeignClient userFeignClient;

//    public List<User> getUsers() {
//        return userFeignClient.getUsers();
//    }

//    public User getUserById(Long id) {
//        return userFeignClient.getUserById(id);
//    }

//    public User saveUser(User user) {
//        return userFeignClient.addUser(user);
//    }

    public UserTokenDto authenticate(UserLoginDto userLoginDto) {
        return userFeignClient.authenticate(userLoginDto);
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        return userFeignClient.createUser(userCreateDto);
    }

    public String getCurrentUserName(String authorization) {
        return userFeignClient.getCurrentUserName(authorization);
    }

    public UserDto getCurrentUser(String authorization){
        return userFeignClient.getCurrentUser(authorization);
    }

    public ResponseEntity<String> changePassword(UserChangePasswordDto userChangePasswordDto, String authHeader) {
        return userFeignClient.changePassword(userChangePasswordDto, authHeader);
    }
}
