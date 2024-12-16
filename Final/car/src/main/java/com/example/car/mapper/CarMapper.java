package com.example.car.mapper;

import com.example.car.dto.CarDto;
import com.example.car.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toDto(Car car);
    Car toEntity(CarDto carDto);

    List<CarDto> toDtoList(List<Car> cars);
}
