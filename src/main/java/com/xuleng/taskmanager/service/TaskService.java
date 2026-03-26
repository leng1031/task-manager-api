package com.xuleng.taskmanager.service;

import com.xuleng.taskmanager.entity.Task;
import com.xuleng.taskmanager.entity.TaskStatus;
import com.xuleng.taskmanager.exception.TaskNotFoundException;
import com.xuleng.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Create Task
    public Task createTask(Task task){
        task.setCreatedAt(LocalDateTime.now());

        if (task.getStatus() == null){
            task.setStatus(TaskStatus.TODO);
        }
        return  taskRepository.save(task);
    }

    //Get all tasks
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    //Get task by id
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() ->new TaskNotFoundException("Task not found"));
    }
}
