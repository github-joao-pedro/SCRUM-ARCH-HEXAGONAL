package api.scrum.tag.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.scrum.tag.infrastructure.entities.TagEntity;

public interface JpaTagRepository extends JpaRepository<TagEntity, UUID> {
    
    @Query("FROM TagEntity t WHERE t.project.id = :projectId")
    Optional<List<TagEntity>> findByProjectId(@Param("projectId") UUID projectId);
}
