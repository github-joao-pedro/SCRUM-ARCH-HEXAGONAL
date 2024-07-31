package api.scrum.project.application.usecases;

import api.scrum.user.domain.model.UserPublic;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.read.ReadProjectRequestDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectResponseDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;

public class ReadProjectUseCaseImpl implements ReadProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public ReadProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReadProjectResponseDTO readProject(ReadProjectRequestDTO requestDTO) {
        if (requestDTO.getId() != null) {
            Project project = this.projectRepositoryPort.findById(requestDTO.getId())
                .orElseThrow(() -> new ApplicationException(404, "Project not found with 'id': "+requestDTO.getId(), "Provide a valid 'id'"));

            List<UserPublic> users = this.relationUserProjectRepositoryPort.findUsersByProjectId(project.getId())
                .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"))
                .stream().map(user -> this.modelMapper.map(user, UserPublic.class)).toList();

            ReadProjectResponseDTO response = this.modelMapper.map(project, ReadProjectResponseDTO.class);
            response.setUsers(users);
            return response;
        } else {
            throw new ApplicationException(400,"Invalid request","Provide a valid 'id'");
        }
    }
    
}
