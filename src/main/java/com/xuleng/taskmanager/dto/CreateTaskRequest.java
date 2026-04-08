package com.xuleng.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title can not be empty")
    private String title;

    private String description;
}
