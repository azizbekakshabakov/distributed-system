package com.service.main.api;

import com.service.main.dto.CarDto;
import com.service.main.dto.UsernameRequest;
import com.service.main.feign.CarFeignClient;
import com.service.main.service.CarSender;
import com.service.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarSender carSender;
    private final UserService userService;
    private final ImageController imageController;
    private final CarFeignClient carFeignClient;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<String> addCar(@ModelAttribute CarDto carDto, @RequestParam("image") MultipartFile file, HttpServletRequest request) {
        carDto.setFileName(imageController.upload(file).getBody());

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            carDto.setUsername(userService.getCurrentUserName(authHeader));
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }

        carSender.sendCar(carDto);

        return ResponseEntity.ok("Car added successfully");
    }

    @PutMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<String> editCar(@ModelAttribute CarDto carDto, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        CarDto carFoundByIdDto = carFeignClient.getCarById(carDto.getId());

        String carUsername = carFoundByIdDto.getUsername();
        String senderUsername = userService.getCurrentUserName(authHeader);
        if (carUsername.equals(senderUsername)) {
            carDto.setUsername(carFoundByIdDto.getUsername());
            carDto.setFileName(carFoundByIdDto.getFileName());

            carSender.editCar(carDto);

            return ResponseEntity.ok("Car edited successfully");
        }

        return ResponseEntity.ok("Car edit failed");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CarDto>> getAllCars(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        UsernameRequest usernameRequest = new UsernameRequest();
        if (authHeader != null) {
            usernameRequest.setUsername(userService.getCurrentUserName(authHeader));
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }

        List<CarDto> cars = carFeignClient.getCars(usernameRequest);

        return ResponseEntity.ok(cars);
    }
}
