package com.sony.taskservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateTodoRequest {
    @NotBlank
    private String name;
}
