package com.example1.demo.mapper;

import com.example1.demo.dto.TaskDto;
import com.example1.demo.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "title", target = "taskTitle")
    @Mapping(source = "description", target = "taskDescription")
    @Mapping(source = "deadlineDate", target = "taskDeadlineDate")
    @Mapping(source = "status", target = "taskStatus")
    TaskDto toDto(Task task);

    @Mapping(source = "taskTitle", target = "title")
    @Mapping(source = "taskDescription", target = "description")
    @Mapping(source = "taskDeadlineDate", target = "deadlineDate")
    @Mapping(source = "taskStatus", target = "status")
    Task toEntity(TaskDto taskDto);

    List<TaskDto> toDtoList(List<Task> tasks);
}
