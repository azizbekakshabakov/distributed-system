package com.task2.micro01.micro01.service;

import com.task2.micro01.micro01.dto.UserDto;
import com.task2.micro01.micro01.mapper.UserMapper;
import com.task2.micro01.micro01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    public UserDto addUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public UserDto updateUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
