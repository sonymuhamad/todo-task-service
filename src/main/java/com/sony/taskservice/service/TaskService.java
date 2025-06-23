package com.sony.taskservice.service;

import com.sony.taskservice.dto.request.CreateTaskRequest;
import com.sony.taskservice.dto.request.CreateTodoRequest;
import com.sony.taskservice.dto.response.task.TaskResponse;
import com.sony.taskservice.enums.TodoStatus;
import com.sony.taskservice.exception.DuplicateException;
import com.sony.taskservice.model.Task;
import com.sony.taskservice.model.Todo;
import com.sony.taskservice.repository.TaskRepository;
import com.sony.taskservice.repository.TodoRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TodoRepository todoRepository;

    private List<Todo> validateCreateTodos(CreateTaskRequest reqBody){
        List<CreateTodoRequest> todos = reqBody.getTodos();

        Set<String> nameSet = new HashSet<>();
        for (CreateTodoRequest todo : todos) {
            String name = todo.getName().trim().toLowerCase(); // normalize if needed
            if (!nameSet.add(name)) {
                throw new IllegalArgumentException("Duplicate todo name: " + name);
            }
        }

        return todos.stream().map(createTodoRequest -> {
            Todo todo = new Todo();
            todo.setName(createTodoRequest.getName());
            todo.setStatus(TodoStatus.SCHEDULED);
            return todo;
        }).collect(Collectors.toList());
    }

    public TaskResponse createTask(CreateTaskRequest reqBody, String userId) {
        List<Todo> todos = this.validateCreateTodos(reqBody);

        Optional<Task> existingTask = taskRepository.findByNameAndUserIdAndDeletedAtIsNull(reqBody.getName(),userId);
        if (existingTask.isPresent()) {
            throw new DuplicateException("Task with such name already exists");
        }

        Task task = new Task();
        task.setName(reqBody.getName());
        task.setDescription(reqBody.getDescription());
        task.setUserId(userId);

        Task createdTask = taskRepository.save(task);

        todos = todos.stream().map(todo ->{
            todo.setTaskID(createdTask.getId());

            return todo;
        }).toList();

        List<Todo> createdTodos = todoRepository.saveAll(todos);

        return new TaskResponse(createdTask,createdTodos);
    }
}
