package api.scrum.relation_tag_task.infrastructure.adapter;

import org.modelmapper.ModelMapper;

import api.scrum.relation_tag_task.domain.model.RelationTagTask;
import api.scrum.relation_tag_task.domain.ports.out.RelationTagTaskRepositoryPort;
import api.scrum.relation_tag_task.infrastructure.entities.RelationTagTaskEntity;
import api.scrum.relation_tag_task.infrastructure.repositories.JpaRelationTagTaskRepository;

public class JpaRelationTagTaskAdapter implements RelationTagTaskRepositoryPort {

    private final JpaRelationTagTaskRepository jpaRelationTagTaskRepository;
    private final ModelMapper modelMapper;
    
    public JpaRelationTagTaskAdapter(JpaRelationTagTaskRepository jpaRelationTagTaskRepository,
            ModelMapper modelMapper) {
        this.jpaRelationTagTaskRepository = jpaRelationTagTaskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RelationTagTask save(RelationTagTask relationTagTask) {
        RelationTagTaskEntity relationTagTaskEntity = modelMapper.map(relationTagTask, RelationTagTaskEntity.class);
        RelationTagTaskEntity saved = this.jpaRelationTagTaskRepository.save(relationTagTaskEntity);
        return this.modelMapper.map(saved, RelationTagTask.class);
    }

    @Override
    public void delete(RelationTagTask relationTagTask) {
        this.jpaRelationTagTaskRepository.delete(this.modelMapper.map(relationTagTask, RelationTagTaskEntity.class));
    }
    
}
