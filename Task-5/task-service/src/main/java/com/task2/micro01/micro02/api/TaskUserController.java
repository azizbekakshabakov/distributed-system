package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.dto.TaskUserDto;
import com.task2.micro01.micro02.dto.User;
import com.task2.micro01.micro02.service.TaskService;
import com.task2.micro01.micro02.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequestMapping("/task-user")
@RequiredArgsConstructor
public class TaskUserController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("{id}")
    public TaskDto findById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            TaskDto task = taskService.getTask(authHeader, id);
            return task;
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }
}
