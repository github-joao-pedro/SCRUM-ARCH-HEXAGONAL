package api.scrum.relation_user_task.domain.ports.out;

import api.scrum.relation_user_task.domain.model.RelationUserTask;

public interface RelationUserTaskRepositoryPort {
    RelationUserTask save(RelationUserTask relationUserTask);
    void delete(RelationUserTask relationUserTask);
}
