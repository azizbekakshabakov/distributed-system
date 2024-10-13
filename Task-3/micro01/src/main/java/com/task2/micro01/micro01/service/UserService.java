package com.task2.micro01.micro01.service;

import com.task2.micro01.micro01.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserById(Long id);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
