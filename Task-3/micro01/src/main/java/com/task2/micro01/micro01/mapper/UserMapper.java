package com.task2.micro01.micro01.mapper;

import com.task2.micro01.micro01.dto.UserDto;
import com.task2.micro01.micro01.model.UserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserModel user);
    UserModel toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<UserModel> userModels);
}
