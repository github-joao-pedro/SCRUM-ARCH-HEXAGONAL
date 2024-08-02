package api.scrum.sprint.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.sprint.infrastructure.entities.SprintEntity;

public interface JpaSprintRepository extends JpaRepository<SprintEntity, UUID>{
    
}
