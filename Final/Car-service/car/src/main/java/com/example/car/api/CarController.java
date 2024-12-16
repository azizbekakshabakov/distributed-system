package com.example.car.api;

import com.example.car.dto.CarDto;
import com.example.car.dto.UsernameRequest;
import com.example.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/car")
public class CarController {
    private final CarService carService;

    @PostMapping("/get-all")
    public List<CarDto> getCars(@RequestBody UsernameRequest usernameRequest) {
        return carService.getCars(usernameRequest.getUsername());
    }

//    @GetMapping(value = "/{id}")
//    public UserDto getUser(@PathVariable(name = "id") Long id) {
//        return userService.getUserById(id);
//    }

    @PostMapping
    public CarDto addUser(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

//    @PutMapping
//    public UserDto updateUser(@RequestBody UserDto userDto) {
//        return userService.updateUser(userDto);
//    }

//    @DeleteMapping("{id}")
//    public void deleteUser(@PathVariable(name = "id") Long id) {
//        userService.deleteUser(id);
//    }
}
