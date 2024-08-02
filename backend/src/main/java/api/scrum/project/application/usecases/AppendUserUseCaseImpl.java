package api.scrum.project.application.usecases;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.model.UserPublic;
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
        
        Project projectExiting = this.projectRepositoryPort.findById(requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "Project not found", "The project you are trying to add the user to does not exist"));
        
        User userExiting = this.userRepositoryPort.findById(requestDTO.getUserId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to add does not exist"));
        
        Optional<RelationUserProject> relationExisting = this.relationUserProjectRepositoryPort.findByUserAndProjectId(requestDTO.getUserId(), requestDTO.getProjectId());
        if (relationExisting.isPresent()) {
            throw new ApplicationException(409, "User already a member", "The user is already a participant in the project");
        }

        RelationUserProject newRelation = RelationUserProject.builder().user(userExiting).project(projectExiting).role(requestDTO.getRole()).build();
        relationUserProjectRepositoryPort.save(newRelation);

        
        List<RelationUserProject> relations = this.relationUserProjectRepositoryPort.findByProjectId(projectExiting.getId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));
        List<UserPublic> users = relations.stream().map(relation -> {
            UserPublic userPublic = this.modelMapper.map(relation.getUser(), UserPublic.class);
            userPublic.setRole(relation.getRole());
            return userPublic;
        }).toList();
        UsersResponseDTO responseDTO = this.modelMapper.map(projectExiting, UsersResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
