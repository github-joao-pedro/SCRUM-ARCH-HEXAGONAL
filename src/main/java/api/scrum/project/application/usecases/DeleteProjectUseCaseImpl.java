package api.scrum.project.application.usecases;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.delete.DeleteProjectRequestDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectResponseDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.user.domain.model.UserPublic;

public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {
    
    private final ProjectRepositoryPort projectRepositoryPort;
    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public DeleteProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteProjectResponseDTO deleteProject(DeleteProjectRequestDTO requestDTO) {

        List<UserPublic> users = this.relationUserProjectRepositoryPort.findUsersByProjectId(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"))
            .stream().map(user -> this.modelMapper.map(user, UserPublic.class)).toList();

        Optional<List<RelationUserProject>> relationUserProject = this.relationUserProjectRepositoryPort.findByProjectId(requestDTO.getId());
        relationUserProject.ifPresent(this.relationUserProjectRepositoryPort::deleteAll);

        Project project = this.projectRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found", "The project you are trying to delete does not exist"));
        this.projectRepositoryPort.delete(project);

        DeleteProjectResponseDTO responseDTO = this.modelMapper.map(project, DeleteProjectResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    

}
