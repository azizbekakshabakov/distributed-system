package com.task2.micro01.micro02.mapper;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "title", target = "taskTitle")
    @Mapping(source = "description", target = "taskDescription")
    @Mapping(source = "deadlineDate", target = "taskDeadlineDate")
    @Mapping(source = "status", target = "taskStatus")
    @Mapping(source = "userId", target = "taskUserId")
    TaskDto toDto(Task task);

    @Mapping(source = "taskTitle", target = "title")
    @Mapping(source = "taskDescription", target = "description")
    @Mapping(source = "taskDeadlineDate", target = "deadlineDate")
    @Mapping(source = "taskStatus", target = "status")
    @Mapping(source = "taskUserId", target = "userId")
    Task toEntity(TaskDto taskDto);

    List<TaskDto> toDtoList(List<Task> tasks);
}
