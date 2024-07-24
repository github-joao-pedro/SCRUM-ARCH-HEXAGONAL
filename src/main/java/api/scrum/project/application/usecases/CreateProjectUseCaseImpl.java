package api.scrum.project.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.create.CreateProjectRequestDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectResponseDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;

public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;

    public CreateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO) {
        Project project = this.modelMapper.map(requestDTO, Project.class);
        Project savedProject = this.projectRepositoryPort.save(project);
        return this.modelMapper.map(savedProject, CreateProjectResponseDTO.class);
    }
    
}
