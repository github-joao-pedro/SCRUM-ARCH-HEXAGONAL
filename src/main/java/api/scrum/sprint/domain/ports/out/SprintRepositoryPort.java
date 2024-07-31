package api.scrum.sprint.domain.ports.out;

import java.util.Optional;
import java.util.UUID;

import api.scrum.sprint.domain.model.Sprint;

public interface SprintRepositoryPort {
    Sprint save(Sprint sprint);
    Optional<Sprint> findById(UUID id);
    boolean existsById(UUID id);
    void delete(Sprint sprint);
}
