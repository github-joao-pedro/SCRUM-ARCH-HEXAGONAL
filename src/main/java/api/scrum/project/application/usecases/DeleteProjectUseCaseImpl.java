package api.scrum.project.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.delete.DeleteProjectRequestDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectResponseDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;

public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {
    
    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public DeleteProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteProjectResponseDTO deleteProject(DeleteProjectRequestDTO requestDTO) {
        Project project = this.projectRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found", "The project you are trying to delete does not exist"));
        this.projectRepositoryPort.delete(project);
        return this.modelMapper.map(project, DeleteProjectResponseDTO.class);
    }
    

}
