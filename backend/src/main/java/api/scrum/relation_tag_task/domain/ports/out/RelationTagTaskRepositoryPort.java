package api.scrum.relation_tag_task.domain.ports.out;

import api.scrum.relation_tag_task.domain.model.RelationTagTask;

public interface RelationTagTaskRepositoryPort {
    RelationTagTask save(RelationTagTask relationTagTask);
    void delete(RelationTagTask relationTagTask);
}
