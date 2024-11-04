package com.task2.micro01.micro02.service;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.dto.UserDto;
import com.task2.micro01.micro02.mapper.TaskMapper;
import com.task2.micro01.micro02.model.Task;
import com.task2.micro01.micro02.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    public List<TaskDto> getTasks(String authToken) {
        UserDto userDto = userService.getCurrentUser(authToken);

        List<TaskDto> tasksDto = taskMapper.toDtoList(taskRepository.findByUsername(userDto.getUsername()));
        for (TaskDto taskDto : tasksDto) {
//            User user = userService.getUserById(taskDto.getUserId());
            taskDto.setUserEmail(userDto.getEmail());
            taskDto.setUserFirstName(userDto.getFirstName());
            taskDto.setUserLastName(userDto.getLastName());
//            taskDto.setUsername(userDto.getUsername());
        }

        return tasksDto;
    }

    public TaskDto getTask(String authToken, Long id) {
        TaskDto taskDto = taskMapper
                .toDto(taskRepository.findById(id).orElse(null));

        UserDto userDto = userService.getCurrentUser(authToken);
//        User user = userService.getUserById(taskDto.getUserId());
        taskDto.setUserEmail(userDto.getEmail());
        taskDto.setUserFirstName(userDto.getFirstName());
        taskDto.setUserLastName(userDto.getLastName());

        if (taskDto.getUsername().equals(userDto.getUsername())) {
            return taskDto;
        }
        return null;
    }

    public TaskDto addTask(String authToken, TaskDto taskDto) {
        UserDto userDto = userService.getCurrentUser(authToken);

        taskDto.setUsername(userDto.getUsername());

        return taskMapper
                .toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    public TaskDto updateTask(String authToken, TaskDto taskDto) {
        UserDto userDto = userService.getCurrentUser(authToken);
        Task supposedTask = taskRepository.findById(taskDto.getId()).orElse(null);

        if (userDto.getUsername().equals(supposedTask.getUsername())) {
            taskDto.setUsername(supposedTask.getUsername());

            return taskMapper
                    .toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
        } else {
            return null;
        }
    }

    public void deleteTask(String authToken, Long id) {
        UserDto userDto = userService.getCurrentUser(authToken);
        Task supposedTask = taskRepository.findById(id).orElse(null);

        if (userDto.getUsername().equals(supposedTask.getUsername())) {
            taskRepository.deleteById(id);
        }
    }
}
