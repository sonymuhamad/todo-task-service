package com.sony.taskservice.dto.response.todo;

import com.sony.taskservice.enums.TodoStatus;
import com.sony.taskservice.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
public class TodoResponse {
    private String id;
    private String name;
    private TodoStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public TodoResponse(Todo todo){
        this.id = todo.getId();
        this.name = todo.getName();
        this.status = todo.getStatus();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}
