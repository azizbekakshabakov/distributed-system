package com.example.car.service;

import com.example.car.dto.CarDto;
import com.example.car.mapper.CarMapper;
import com.example.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

//    public List<UserDto> getUsers() {
//        return userMapper.toDtoList(userRepository.findAll());
//    }

//    public UserDto getUserById(Long id) {
//        return userMapper.toDto(userRepository.findById(id).orElse(null));
//    }

    public CarDto addCar(CarDto carDto) {
        return carMapper.toDto(carRepository.save(carMapper.toEntity(carDto)));
    }

//    public UserDto updateUser(UserDto userDto) {
//        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
//    }

//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
}