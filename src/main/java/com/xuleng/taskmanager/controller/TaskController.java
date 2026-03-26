package com.xuleng.taskmanager.controller;

import com.xuleng.taskmanager.common.ApiResponse;
import com.xuleng.taskmanager.entity.Task;
import com.xuleng.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks" )
public class TaskController {

    private final TaskService  taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ApiResponse<Task> createTask(@Valid @RequestBody Task task){
        //return taskService.createTask(task);
        return new ApiResponse<>(200,"success",taskService.createTask(task));
    }

    @GetMapping
    public ApiResponse<List<Task>> getAllTasks (){
        //return taskService.getAllTask();
        return new ApiResponse<>(200,"success",taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public ApiResponse<Task> getTaskById (@PathVariable Long id){
        //return taskService.getTaskById(id);
        return new ApiResponse<>(200,"success",taskService.getTaskById(id));
    }


}
