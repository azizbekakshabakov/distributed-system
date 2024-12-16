package com.example.car.listener;

import com.example.car.dto.CarDto;
import com.example.car.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarListener {
    private final CarService carService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "message-exchange",
                    type = ExchangeTypes.DIRECT),
            value = @Queue(value = "message-queue"),
            key = "carkey"
    ))
    public void receiveMessage(CarDto carDto) {
        carService.addCar(carDto);
    }
}
