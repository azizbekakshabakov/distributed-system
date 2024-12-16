package com.service.main.api;

import com.service.main.dto.CarDto;
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

    @GetMapping(value = "")
    public ResponseEntity<List<CarDto>> getAllCars(HttpServletRequest request) {

    }
}
