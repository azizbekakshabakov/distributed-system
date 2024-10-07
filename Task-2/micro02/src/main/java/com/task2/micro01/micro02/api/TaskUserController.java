package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.dto.TaskUserDto;
import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.service.TaskService;
import com.task2.micro01.micro02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task-user")
@RequiredArgsConstructor
public class TaskUserController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("{id}")
    public TaskUserDto findById(@PathVariable(name = "id") Long id) {
        TaskDto task = taskService.getTask(id);
        User user = userService.getUserById(task.getTaskUserId());

        TaskUserDto taskUser = new TaskUserDto(
                task.getId(),
                task.getTaskTitle(),
                task.getTaskDescription(),
                task.getTaskDeadlineDate(),
                task.getTaskStatus(),
                user
        );

        return taskUser;
    }
}
