package api.scrum.project.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import api.scrum.project.infrastructure.entities.ProjectEntity;

public interface JpaProjectRepository extends CrudRepository<ProjectEntity, UUID>{
    
}
