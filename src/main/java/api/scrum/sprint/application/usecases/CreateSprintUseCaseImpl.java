package api.scrum.sprint.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.in.create.CreateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.create.CreateSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.create.CreateSprintUseCase;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;

public class CreateSprintUseCaseImpl implements CreateSprintUseCase {

    private final SprintRepositoryPort sprintRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public CreateSprintUseCaseImpl(SprintRepositoryPort sprintRepositoryPort,
            ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.sprintRepositoryPort = sprintRepositoryPort;
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateSprintResponseDTO createSprint(CreateSprintRequestDTO requestDTO) {
        Project project = this.projectRepositoryPort.findById(requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found with 'id': "+requestDTO.getProjectId(), "Provide a valid 'id'"));
        
        Sprint sprint = this.modelMapper.map(requestDTO, Sprint.class);
        sprint.setProject(project);
        Sprint sprintSaved = this.sprintRepositoryPort.save(sprint);

        CreateSprintResponseDTO responseDTO = this.modelMapper.map(sprintSaved, CreateSprintResponseDTO.class);
        responseDTO.setProject(project);
        return responseDTO;
    }
    
}
