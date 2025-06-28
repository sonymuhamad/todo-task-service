package com.sony.taskservice.grpc;

import com.sony.taskservice.model.Task;
import com.sony.taskservice.service.TaskService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class TaskServiceGrpcImpl extends TaskServiceGrpc.TaskServiceImplBase {

    @Autowired
    private TaskService taskService;

    @Override
    public void getTaskByID(GetTaskByIDRequest request, StreamObserver<com.sony.taskservice.grpc.TaskResponse> responseObserver) {
        // Dummy data — replace with DB call
        if (request.getId().isBlank()) {
            throw new IllegalArgumentException("User ID is required");
        }

        com.sony.taskservice.dto.response.task.TaskResponse task = taskService.getByID(request.getId());

        List<Todo> todosResponse = task.getTodos().stream().map(todo->{
            return Todo.newBuilder().
                    setId(todo.getId()).
                    setName(todo.getName()).
                    setStatus(todo.getStatus().
                            toString()).build();
        }).toList();

        com.sony.taskservice.grpc.TaskResponse response = TaskResponse.newBuilder().
                setId(task.getId()).
                setName(task.getName()).
                setDescription(task.getDescription()).
                setUserId(task.getUserId()).
                addAllTodos(todosResponse).
                build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
