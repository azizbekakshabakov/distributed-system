package com.task2.micro01.micro02.api;

import com.task2.micro01.micro02.dto.TaskDto;
import com.task2.micro01.micro02.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<TaskDto> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping(value = "{id}")
    public TaskDto getTask(@PathVariable(name = "id") Long id){
        return taskService.getTask(id);
    }

    @PostMapping()
    public TaskDto addTask(@RequestBody TaskDto taskDto){
        return taskService.addTask(taskDto);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteTask(@PathVariable(name = "id") Long id){
        taskService.deleteTask(id);
    }
}
