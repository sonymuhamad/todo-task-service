package com.sony.taskservice.controller;

import com.sony.taskservice.dto.request.CreateTaskRequest;
import com.sony.taskservice.dto.response.BaseResponse;
import com.sony.taskservice.dto.response.task.TaskResponse;
import com.sony.taskservice.model.Task;
import com.sony.taskservice.model.Todo;
import com.sony.taskservice.service.TaskService;
import com.sony.taskservice.util.Parser;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(
            @RequestHeader("user-id") String userIdString,
            @Valid @RequestBody CreateTaskRequest reqBody) {

        TaskResponse taskResponse = taskService.createTask(reqBody,userIdString);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(taskResponse,"Register success", HttpStatus.CREATED));
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getTask(@RequestHeader("user-id") String userIdString){
        List<TaskResponse> tasksResponse = taskService.getByUserId(userIdString);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(tasksResponse,"", HttpStatus.OK));
    }

    // delete task
}
