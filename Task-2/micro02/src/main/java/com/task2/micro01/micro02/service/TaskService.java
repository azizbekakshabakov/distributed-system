package com.task2.micro01.micro02.service;

import com.task2.micro01.micro02.dto.TaskDto;
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
    public List<TaskDto> getTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    public TaskDto getTask(Long id) {
        return taskMapper
                .toDto(taskRepository.findById(id).orElse(null));
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
