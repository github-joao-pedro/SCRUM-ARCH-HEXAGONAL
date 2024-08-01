package api.scrum.backlog.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.scrum.backlog.infrastructure.entities.BacklogEntity;

public interface JpaBacklogRepository extends JpaRepository<BacklogEntity, UUID>{
    Optional<BacklogEntity> findByProjectId(UUID projectId);
}
