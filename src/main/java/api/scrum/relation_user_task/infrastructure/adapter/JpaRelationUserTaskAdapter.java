package api.scrum.relation_user_task.infrastructure.adapter;

import org.modelmapper.ModelMapper;

import api.scrum.relation_user_task.domain.model.RelationUserTask;
import api.scrum.relation_user_task.domain.ports.out.RelationUserTaskRepositoryPort;
import api.scrum.relation_user_task.infrastructure.entities.RelationUserTaskEntity;
import api.scrum.relation_user_task.infrastructure.repositories.JpaRelationUserTaskRepository;

public class JpaRelationUserTaskAdapter implements RelationUserTaskRepositoryPort {

    private final JpaRelationUserTaskRepository jpaRelationTagTaskRepository;
    private final ModelMapper modelMapper;
    
    public JpaRelationUserTaskAdapter(JpaRelationUserTaskRepository jpaRelationTagTaskRepository,
            ModelMapper modelMapper) {
        this.jpaRelationTagTaskRepository = jpaRelationTagTaskRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public RelationUserTask save(RelationUserTask relationUserTask) {
        RelationUserTaskEntity relationUserTaskEntity = this.modelMapper.map(relationUserTask, RelationUserTaskEntity.class);
        RelationUserTaskEntity savedRelationUserTaskEntity = this.jpaRelationTagTaskRepository.save(relationUserTaskEntity);
        return this.modelMapper.map(savedRelationUserTaskEntity, RelationUserTask.class);
    }

    @Override
    public void delete(RelationUserTask relationUserTask) {
        this.jpaRelationTagTaskRepository.delete(this.modelMapper.map(relationUserTask, RelationUserTaskEntity.class));
    }
    
}
