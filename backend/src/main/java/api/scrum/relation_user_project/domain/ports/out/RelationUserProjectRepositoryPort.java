package api.scrum.relation_user_project.domain.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.scrum.project.domain.model.Project;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.user.domain.model.User;

public interface RelationUserProjectRepositoryPort {
    RelationUserProject save(RelationUserProject relationUserProject);
    Optional<List<User>> findUsersByProjectId(UUID projectId);
    Optional<List<Project>> findProjectByUserId(UUID userId);
    Optional<List<RelationUserProject>> findByProjectId(UUID projectId);
    Optional<List<RelationUserProject>> findByUserId(UUID userId);
    Optional<RelationUserProject> findById(UUID id);
    Optional<RelationUserProject> findByUserAndProjectId(UUID userId, UUID projectId);
    void delete(RelationUserProject relationUserProject);
    void deleteAll(List<RelationUserProject> relationUserProject);
}
