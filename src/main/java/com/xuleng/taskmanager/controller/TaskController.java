package com.xuleng.taskmanager.controller;

import com.xuleng.taskmanager.common.ApiResponse;
import com.xuleng.taskmanager.dto.CreateTaskRequest;
import com.xuleng.taskmanager.dto.TaskResponse;
import com.xuleng.taskmanager.entity.Task;
import com.xuleng.taskmanager.entity.TaskStatus;
import com.xuleng.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
        public ApiResponse<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request){
        //return taskService.createTask(task);
        return new ApiResponse<>(200,"success",taskService.createTask(request));
    }

    @GetMapping
    public ApiResponse<List<TaskResponse>> getAllTasks (){
        //return taskService.getAllTask();
        List<TaskResponse> list = taskService.getAllTask();
        return new ApiResponse<>(200,"success",list);
    }

    @GetMapping("/{id}")
    public ApiResponse<Task> getTaskById (@PathVariable Long id){
        //return taskService.getTaskById(id);
        return new ApiResponse<>(200,"success",taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        return new ApiResponse<>(200,"updated",taskService.updateTask(id,task));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTask (@PathVariable long id){
        taskService.deleteTask(id);
        return new ApiResponse<>(200,"deleted",null);
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<Task> updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status
            ){
        return new ApiResponse<>(200,"Status updated",taskService.updateStatus(id,status));
    }

    @GetMapping("/page")
    public ApiResponse<Page<Task>> getTasks(
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ApiResponse<>(200,"success", taskService.getTask(page, size));
    }

}
