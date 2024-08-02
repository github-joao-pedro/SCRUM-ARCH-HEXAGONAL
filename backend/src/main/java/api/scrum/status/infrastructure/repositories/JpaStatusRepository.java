package api.scrum.status.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.scrum.status.infrastructure.entities.StatusEntity;

public interface JpaStatusRepository extends JpaRepository<StatusEntity, UUID> {
    
    @Query("FROM StatusEntity s WHERE s.project.id = :projectId")
    Optional<List<StatusEntity>> findByProjectId(@Param("projectId") UUID projectId);
}
