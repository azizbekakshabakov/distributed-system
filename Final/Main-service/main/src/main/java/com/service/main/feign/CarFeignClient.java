package com.service.main.feign;

import com.service.main.dto.CarDto;
import com.service.main.dto.UsernameRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "car-feign-client", url = "${feign.car.url}")
public interface CarFeignClient {
    @PostMapping(value = "/car/get-all")
    List<CarDto> getCars(@RequestBody UsernameRequest usernameRequest);
}
