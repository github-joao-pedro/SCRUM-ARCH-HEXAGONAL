package api.scrum.task.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.task.infrastructure.entities.TaskEntity;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, UUID> {
    
}
