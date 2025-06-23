package com.sony.taskservice.repository;

import com.sony.taskservice.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo,String> {
}
