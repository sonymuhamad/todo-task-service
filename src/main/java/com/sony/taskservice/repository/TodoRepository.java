package com.sony.taskservice.repository;

import com.sony.taskservice.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo,String> {
    List<Todo> findByTaskID(String taskId);
}
