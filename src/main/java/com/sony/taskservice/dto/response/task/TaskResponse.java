package com.sony.taskservice.dto.response.task;

import com.sony.taskservice.dto.response.todo.TodoResponse;
import com.sony.taskservice.model.Task;
import com.sony.taskservice.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class TaskResponse {
    private String id;
    private String name;
    private String userId;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private List<TodoResponse> todos;

    public TaskResponse(Task task, List<Todo> todos) {
        this.id = task.getId();
        this.name = task.getName();
        this.userId = task.getUserId();
        this.description = task.getDescription();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.todos = todos.stream().map(TodoResponse::new).toList();
    }
}
