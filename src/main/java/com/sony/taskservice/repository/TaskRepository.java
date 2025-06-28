package com.sony.taskservice.repository;

import com.sony.taskservice.model.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findByName(String name);
    Optional<Task> findByNameAndUserIdAndDeletedAtIsNull(String name, String userId);
    Optional<Task> findByIdAndDeletedAtIsNull(String id);
}
