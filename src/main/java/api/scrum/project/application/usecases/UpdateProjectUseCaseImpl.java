package api.scrum.project.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.update.UpdateProjectRequestDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectResponseDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;

public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public UpdateProjectResponseDTO updateProject(UpdateProjectRequestDTO requestDTO) {
        Project existingProject = this.projectRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found", "The project you are trying to update does not exist"));
        
        if (requestDTO.getName() != null) {
            existingProject.setName(requestDTO.getName());
        }
        if (requestDTO.getDescription() != null) {
            existingProject.setDescription(requestDTO.getDescription());
        }
        if (requestDTO.getDateFrom() != null) {
            existingProject.setDateFrom(requestDTO.getDateFrom());
        }
        if (requestDTO.getDateTo() != null) {
            existingProject.setDateTo(requestDTO.getDateTo());
        }
        if (requestDTO.getSprintDuration() != null) {
            existingProject.setSprintDuration(requestDTO.getSprintDuration());
        }
        if (requestDTO.getSprintAmmount() != null) {
            existingProject.setSprintAmmount(requestDTO.getSprintAmmount());
        }

        return this.modelMapper.map(this.projectRepositoryPort.save(existingProject), UpdateProjectResponseDTO.class);
    }
    
}
