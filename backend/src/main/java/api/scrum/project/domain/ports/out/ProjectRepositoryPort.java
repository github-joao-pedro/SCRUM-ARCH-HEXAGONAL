package api.scrum.project.domain.ports.out;

import java.util.Optional;
import java.util.UUID;

import api.scrum.project.domain.model.Project;

public interface ProjectRepositoryPort {
    Project save(Project project);
    Optional<Project> findById(UUID id);
    boolean existsById(UUID id);
    void delete(Project project);
}
