package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.model.UserPublic;
import api.scrum.project.domain.ports.in.update.UpdateProjectRequestDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectResponseDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;

public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
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

        Project newProject = this.projectRepositoryPort.save(existingProject);
        UpdateProjectResponseDTO responseDTO = this.modelMapper.map(newProject, UpdateProjectResponseDTO.class);
        
        List<RelationUserProject> relations = this.relationUserProjectRepositoryPort.findByProjectId(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));
        List<UserPublic> users = relations.stream().map(relation -> {
            UserPublic userPublic = this.modelMapper.map(relation.getUser(), UserPublic.class);
            userPublic.setRole(relation.getRole());
            return userPublic;
        }).toList();
        
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
