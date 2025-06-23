package com.sony.taskservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateTaskRequest {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Size(min = 1,message = "Minimal satu todo")
    private List<@Valid CreateTodoRequest> todos;
}
