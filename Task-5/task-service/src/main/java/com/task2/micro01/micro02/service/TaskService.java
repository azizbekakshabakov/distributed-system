package com.task2.micro01.micro02.service;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.mapper.TaskMapper;
import com.task2.micro01.micro02.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    public List<TaskDto> getTasks() {
        List<TaskDto> tasksDto = taskMapper.toDtoList(taskRepository.findAll());
        for (TaskDto taskDto : tasksDto) {
            User user = userService.getUserById(taskDto.getUserId());
            taskDto.setUserEmail(user.getEmail());
            taskDto.setUserFullName(user.getFullName());
        }

        return tasksDto;
    }

    public TaskDto getTask(Long id) {
        TaskDto taskDto = taskMapper
                .toDto(taskRepository.findById(id).orElse(null));
        User user = userService.getUserById(taskDto.getUserId());
        taskDto.setUserEmail(user.getEmail());
        taskDto.setUserFullName(user.getFullName());

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
        taskRepository.deleteById(id);
    }
}
