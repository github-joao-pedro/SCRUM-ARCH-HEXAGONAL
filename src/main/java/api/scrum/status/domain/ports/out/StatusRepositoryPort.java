package api.scrum.status.domain.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.scrum.status.domain.model.Status;

public interface StatusRepositoryPort {
    Status save(Status status);
    Optional<Status> findById(UUID id);
    Optional<List<Status>> findByProjectId(UUID projectId);
    boolean existsById(UUID id);
    void delete(Status status);
}
