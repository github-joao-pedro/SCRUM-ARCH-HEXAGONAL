package api.scrum.backlog.domain.ports.out;

import java.util.Optional;
import java.util.UUID;

import api.scrum.backlog.domain.model.Backlog;

public interface BacklogRepositoryPort {
    Backlog save(Backlog backlog);
    Optional<Backlog> findById(UUID id);
    Optional<Backlog> findByProjectId(UUID projectId);
    boolean existsById(UUID id);
    void delete(Backlog backlog);
}
