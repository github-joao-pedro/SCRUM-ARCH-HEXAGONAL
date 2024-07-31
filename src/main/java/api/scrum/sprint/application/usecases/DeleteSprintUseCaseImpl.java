package api.scrum.sprint.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintUseCase;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;

public class DeleteSprintUseCaseImpl implements DeleteSprintUseCase {

    private final SprintRepositoryPort sprintRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public DeleteSprintUseCaseImpl(SprintRepositoryPort sprintRepositoryPort,
            ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.sprintRepositoryPort = sprintRepositoryPort;
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteSprintResponseDTO deleteSprint(DeleteSprintRequestDTO requestDTO) {
        Sprint sprint = this.sprintRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Sprint not found with 'id': "+requestDTO.getId(), "Provide a valid 'id'"));
        Project project = this.projectRepositoryPort.findById(sprint.getProject().getId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found with 'id': "+sprint.getProject().getId(), "Provide a valid 'id'"));
        this.sprintRepositoryPort.delete(sprint);
        DeleteSprintResponseDTO responseDTO = this.modelMapper.map(sprint, DeleteSprintResponseDTO.class);
        responseDTO.setProject(project);
        return responseDTO;
    }
    
}
