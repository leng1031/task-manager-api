package com.xuleng.taskmanager.service;

import com.xuleng.taskmanager.dto.CreateTaskRequest;
import com.xuleng.taskmanager.dto.TaskResponse;
import com.xuleng.taskmanager.entity.Task;
import com.xuleng.taskmanager.entity.TaskStatus;
import com.xuleng.taskmanager.exception.TaskNotFoundException;
import com.xuleng.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public TaskResponse createTask(CreateTaskRequest request){
//        task.setCreatedAt(LocalDateTime.now());
//
//        if (task.getStatus() == null){
//            task.setStatus(TaskStatus.TODO);
//        }
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        Task saved = taskRepository.save(task);
        return convertToResponse(saved);
    }

    private TaskResponse convertToResponse(Task task){
        TaskResponse res = new TaskResponse();
        res.setId(task.getId());
        res.setTitle(task.getTitle());
        res.setDescription(task.getDescription());
        res.setStatus(task.getStatus());
        res.setCreateAt(task.getCreatedAt());
        return res;
    }

    //Get all tasks
//    public List<Task> getAllTask(){
//        return taskRepository.findAll();
//    }

    public List<TaskResponse> getAllTask(){
        return taskRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }


    //Get task by id
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() ->new TaskNotFoundException("Task not found"));
    }

    //update Task by task id
    public Task updateTask (Long id, Task newTask){
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));

        existing.setTitle(newTask.getTitle());
        existing.setDescription(newTask.getDescription());
        //existing.setStatus(newTask.getStatus());
        existing.setDueDate(newTask.getDueDate());

        return taskRepository.save(existing);
    }

    //delete Task thru task id
    public void deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public Task updateStatus(Long id, TaskStatus status){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Page<Task> getTask(int page, int size){
        return taskRepository.findAll(PageRequest.of(page, size));
    }

}
