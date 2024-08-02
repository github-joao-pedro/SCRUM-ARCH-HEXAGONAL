package api.scrum.project.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.project.infrastructure.entities.ProjectEntity;
import api.scrum.project.infrastructure.repositories.JpaProjectRepository;

@Component
public class JpaProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final JpaProjectRepository jpaProjectRepository;
    private final ModelMapper modelMapper;

    public JpaProjectRepositoryAdapter(JpaProjectRepository jpaProjectRepository, ModelMapper modelMapper) {
        this.jpaProjectRepository = jpaProjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity projectEntity = this.modelMapper.map(project, ProjectEntity.class);
        ProjectEntity savedProjectEntity = this.jpaProjectRepository.save(projectEntity);
        return this.modelMapper.map(savedProjectEntity, Project.class);
    }

    @Override
    public Optional<Project> findById(UUID id) {
        Optional<ProjectEntity> projectSaved = this.jpaProjectRepository.findById(id);
        if (projectSaved.isPresent()) {
            Project project = this.modelMapper.map(projectSaved.get(), Project.class);
            return Optional.of(project);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaProjectRepository.existsById(id);
    }

    @Override
    public void delete(Project project) {
        this.jpaProjectRepository.delete(this.modelMapper.map(project, ProjectEntity.class));
    }
    
}
