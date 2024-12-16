package com.service.main.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "car-feign-client", url = "${feign.car.url}")
public class CarFeignClient {
    @PostMapping(value = "/car")
    String createCar(CarDto carDto);
}
