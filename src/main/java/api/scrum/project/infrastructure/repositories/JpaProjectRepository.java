package api.scrum.project.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.project.infrastructure.entities.ProjectEntity;

public interface JpaProjectRepository extends JpaRepository<ProjectEntity, UUID>{
    
}
