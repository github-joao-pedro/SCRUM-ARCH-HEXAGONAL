package api.scrum.tag.domain.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.scrum.tag.domain.model.Tag;

public interface TagRepositoryPort {
    Tag save(Tag tag);
    Optional<Tag> findById(UUID id);
    Optional<List<Tag>> findByProjectId(UUID projectId);
    boolean existsById(UUID id);
    void delete(Tag tag);
}
