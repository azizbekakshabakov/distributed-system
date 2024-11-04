package com.task2.micro01.micro02.service;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.mapper.TaskMapper;
import com.task2.micro01.micro02.model.Task;
import com.task2.micro01.micro02.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final CacheService cacheService;

    @Value("${spring.application.name}")
    private String appName;

    public List<TaskDto> getTasks() {
        final String cacheKey = appName + ":tasks";
        List<TaskDto> taskDtos = (List<TaskDto>) cacheService.getObject(cacheKey);
        if (taskDtos != null) {
            return taskDtos;
        }

        taskDtos = taskMapper.toDtoList(taskRepository.findAll());
        for (TaskDto taskDto : taskDtos) {
            User user = userService.getUserById(taskDto.getUserId());
            taskDto.setUserEmail(user.getEmail());
            taskDto.setUserFullName(user.getFullName());
        }
        cacheService.cacheObject(cacheKey, taskDtos, 1, TimeUnit.MINUTES);

        return taskDtos;
    }

    public TaskDto getTask(Long id) {
        final String cacheKey = appName + ":task:" + id;
        TaskDto taskDto = (TaskDto) cacheService.getObject(cacheKey);
        if (taskDto != null) {
            return taskDto;
        }

        Task task = taskRepository.findById(id).orElse(null);
        taskDto = taskMapper.toDto(task);
        User user = userService.getUserById(task.getUserId());
        taskDto.setUserEmail(user.getEmail());
        taskDto.setUserFullName(user.getFullName());
        cacheService.cacheObject(cacheKey, taskDto, 1, TimeUnit.MINUTES);

        return taskDto;
    }

    public TaskDto addTask(TaskDto taskDto) {
        return taskMapper
                .toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    public TaskDto updateTask(TaskDto taskDto) {
        return taskMapper
                .toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    public void deleteTask(Long id) {
        final String cacheKey = appName + ":task:" + id;
        cacheService.deleteObject(cacheKey);

        taskRepository.deleteById(id);
    }
}
