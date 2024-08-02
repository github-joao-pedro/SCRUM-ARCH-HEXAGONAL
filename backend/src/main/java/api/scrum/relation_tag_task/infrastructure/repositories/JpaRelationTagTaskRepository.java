package api.scrum.relation_tag_task.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.relation_tag_task.infrastructure.entities.RelationTagTaskEntity;

public interface JpaRelationTagTaskRepository extends JpaRepository<RelationTagTaskEntity, UUID> {
    
}
