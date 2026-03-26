package com.xuleng.taskmanager.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = " Title can not be empty")
    private String title;

    private String description;

    @Enumerated (EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        if (this.status == null){
            this.status = TaskStatus.TODO;
        }
    }
}
