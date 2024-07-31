package api.scrum.project.application.usecases;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.users.AppendUserUseCase;
import api.scrum.project.domain.ports.in.users.UsersRequestDTO;
import api.scrum.project.domain.ports.in.users.UsersResponseDTO;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class AppendUserUseCaseImpl implements AppendUserUseCase {

    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ModelMapper modelMapper;

    public AppendUserUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, UserRepositoryPort userRepositoryPort,
    RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.projectRepositoryPort = projectRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersResponseDTO appendUser(UsersRequestDTO requestDTO) {
        
        Project project = this.projectRepositoryPort.findById(requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found", "The project you are trying to add the user to does not exist"));
        
        User user = this.userRepositoryPort.findById(requestDTO.getUserId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to add does not exist"));
        
        Optional<RelationUserProject> relationExisting = this.relationUserProjectRepositoryPort.findByUserAndProjectId(requestDTO.getUserId(), requestDTO.getProjectId());
        if (relationExisting.isPresent()) {
            throw new ApplicationException(409, "User already a member", "The user is already a participant in the project");
        }

        RelationUserProject relation = RelationUserProject.builder().user(user).project(project).role(requestDTO.getRole()).build();
        relationUserProjectRepositoryPort.save(relation);

        List<User> users = relationUserProjectRepositoryPort.findUsersByProjectId(requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));

        UsersResponseDTO responseDTO = this.modelMapper.map(project, UsersResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
