package com.service.main.service;

import com.service.main.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarSender {
    private final RabbitTemplate rabbitTemplate;

    public void sendCar(CarDto car) {
        rabbitTemplate.convertAndSend("message-exchange", "carkey", car);
    }
}
