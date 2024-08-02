package api.scrum.task.domain.ports.out;

import java.util.Optional;
import java.util.UUID;

import api.scrum.task.domain.model.Task;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    boolean existsById(UUID id);
    void delete(Task task);
}
