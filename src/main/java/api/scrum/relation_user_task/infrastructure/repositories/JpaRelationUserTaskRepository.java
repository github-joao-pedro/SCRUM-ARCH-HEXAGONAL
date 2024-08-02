package api.scrum.relation_user_task.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.relation_user_task.infrastructure.entities.RelationUserTaskEntity;

public interface JpaRelationUserTaskRepository extends JpaRepository<RelationUserTaskEntity, UUID>{
    
}
