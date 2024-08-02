package api.scrum.relation_user_project.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.scrum.project.infrastructure.entities.ProjectEntity;
import api.scrum.relation_user_project.infrastructure.entities.RelationUserProjectEntity;
import api.scrum.user.infrastructure.entities.UserEntity;

public interface JpaRelationUserProjectRepository extends JpaRepository<RelationUserProjectEntity, UUID> {
    
    @Query("SELECT r.user FROM RelationUserProjectEntity r WHERE r.project.id = :projectId")
    Optional<List<UserEntity>> findUsersByProjectId(UUID projectId);

    @Query("SELECT r.project FROM RelationUserProjectEntity r WHERE r.user.id = :userId")
    Optional<List<ProjectEntity>> findProjectByUserId(UUID userId);
    
    @Query("FROM RelationUserProjectEntity r WHERE r.project.id = :projectId")
    Optional<List<RelationUserProjectEntity>> findByProjectId(@Param("projectId") UUID projectId);

    @Query("FROM RelationUserProjectEntity r WHERE r.user.id = :userId")
    Optional<List<RelationUserProjectEntity>> findByUserId(@Param("userId") UUID userId);

    @Query("FROM RelationUserProjectEntity r WHERE r.user.id = :userId and r.project.id = :projectId")
    Optional<RelationUserProjectEntity> findByUserAndProjectId(@Param("userId") UUID userId, @Param("projectId") UUID projectId);
}
