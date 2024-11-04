package com.task2.micro01.micro01.service;

import com.task2.micro01.micro01.dto.UserDto;
import com.task2.micro01.micro01.mapper.UserMapper;
import com.task2.micro01.micro01.model.UserModel;
import com.task2.micro01.micro01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CacheService cacheService;

    @Value("${spring.application.name}")
    private String appName;

    public List<UserDto> getUsers() {
        final String cacheKey = appName + ":users";
        List<UserDto> userDtos = (List<UserDto>) cacheService.getObject(cacheKey);
        if (userDtos != null) {
            return userDtos;
        }
        userDtos = userMapper.toDtoList(userRepository.findAll());
        cacheService.cacheObject(cacheKey, userDtos, 1, TimeUnit.MINUTES);

        return userDtos;
    }

    public UserDto getUserById(Long id) {
        final String cacheKey = appName + ":user:" + id;
        UserDto user = (UserDto) cacheService.getObject(cacheKey);
        if (user != null) {
            return user;
        }
        Optional<UserModel> userOptional = userRepository.findById(id);
        userOptional.ifPresent(
                u -> cacheService.cacheObject(cacheKey, userMapper.toDto(u), 1, TimeUnit.MINUTES)
        );

        return userMapper.toDto(
                userOptional.orElse(null)
        );
    }

    public UserDto addUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public UserDto updateUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public void deleteUser(Long id) {
        final String cacheKey = appName + ":user:" + id;
        cacheService.deleteObject(cacheKey);

        userRepository.deleteById(id);
    }
}
