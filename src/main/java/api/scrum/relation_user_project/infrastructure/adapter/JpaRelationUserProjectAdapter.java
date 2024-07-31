package api.scrum.relation_user_project.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.scrum.project.domain.model.Project;
import api.scrum.project.infrastructure.entities.ProjectEntity;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.relation_user_project.infrastructure.entities.RelationUserProjectEntity;
import api.scrum.relation_user_project.infrastructure.repositories.JpaRelationUserProjectRepository;
import api.scrum.user.domain.model.User;
import api.scrum.user.infrastructure.entities.UserEntity;

public class JpaRelationUserProjectAdapter implements RelationUserProjectRepositoryPort {

    private final JpaRelationUserProjectRepository relationUserProjectRepository;
    private final ModelMapper modelMapper;

    public JpaRelationUserProjectAdapter(JpaRelationUserProjectRepository relationUserProjectRepository,
            ModelMapper modelMapper) {
        this.relationUserProjectRepository = relationUserProjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<List<User>> findUsersByProjectId(UUID projectId) {
        Optional<List<UserEntity>> userEntity = this.relationUserProjectRepository.findUsersByProjectId(projectId);
        return userEntity.map(entities -> 
            entities.stream()
                .map(entity -> modelMapper.map(entity, User.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<List<Project>> findProjectByUserId(UUID userId) {
        Optional<List<ProjectEntity>> projectEntity = this.relationUserProjectRepository.findProjectByUserId(userId);
        return projectEntity.map(entities -> 
            entities.stream()
                .map(entity -> modelMapper.map(entity, Project.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public RelationUserProject save(RelationUserProject relationUserProject) {
        RelationUserProjectEntity relationUserProjectEntity = this.modelMapper.map(relationUserProject, RelationUserProjectEntity.class);
        RelationUserProjectEntity saved = this.relationUserProjectRepository.save(relationUserProjectEntity);
        return this.modelMapper.map(saved, RelationUserProject.class);
    }

    @Override
    public Optional<RelationUserProject> findById(UUID id) {
        Optional<RelationUserProjectEntity> savedRelation = this.relationUserProjectRepository.findById(id);
        return Optional.of(this.modelMapper.map(savedRelation, RelationUserProject.class));
    }

    @Override
    public void delete(RelationUserProject relationUserProject) {
        RelationUserProjectEntity relationUserProjectEntity = this.modelMapper.map(relationUserProject, RelationUserProjectEntity.class);
        this.relationUserProjectRepository.delete(relationUserProjectEntity);
    }

    @Override
    public Optional<List<RelationUserProject>> findByProjectId(UUID projectId) {
        Optional<List<RelationUserProjectEntity>> relationUserProjectEntity = this.relationUserProjectRepository.findByProjectId(projectId);
        return relationUserProjectEntity.map(entities -> 
            entities.stream()
                .map(entity -> modelMapper.map(entity, RelationUserProject.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<List<RelationUserProject>> findByUserId(UUID userId) {
        Optional<List<RelationUserProjectEntity>> relationUserProjectEntity = this.relationUserProjectRepository.findByUserId(userId);
        return relationUserProjectEntity.map(entities -> 
            entities.stream()
                .map(entity -> modelMapper.map(entity, RelationUserProject.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public void deleteAll(List<RelationUserProject> relationUserProject) {
        List<RelationUserProjectEntity> entityList = relationUserProject.stream().map(relation -> modelMapper.map(relation, RelationUserProjectEntity.class)).collect(Collectors.toList());
        this.relationUserProjectRepository.deleteAll(entityList);
    }

    @Override
    public Optional<RelationUserProject> findByUserAndProjectId(UUID userId, UUID projectId) {
        Optional<RelationUserProjectEntity> entity = relationUserProjectRepository.findByUserAndProjectId(userId, projectId);
        if (entity.isPresent()) {
            RelationUserProject relation = RelationUserProject.builder()
            .id(entity.get().getId())
            .project(this.modelMapper.map(entity.get().getProject(), Project.class))
            .user(this.modelMapper.map(entity.get().getUser(), User.class))
            .role(entity.get().getRole())
            .build();
    
            return Optional.of(relation);
        } else {
            return Optional.empty();
        }

    }
    
}
