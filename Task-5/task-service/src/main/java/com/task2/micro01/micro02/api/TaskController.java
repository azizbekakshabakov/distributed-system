package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<TaskDto> getTasks(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return taskService.getTasks(authHeader);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @GetMapping(value = "{id}")
    public TaskDto getTask(@PathVariable(name = "id") Long id, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return taskService.getTask(authHeader, id);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @PostMapping()
    public TaskDto addTask(@RequestBody TaskDto taskDto, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return taskService.addTask(authHeader, taskDto);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            return taskService.updateTask(authHeader, taskDto);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }

    @DeleteMapping(value = "{id}")
    public void deleteTask(@PathVariable(name = "id") Long id, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            taskService.deleteTask(authHeader, id);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }
}
