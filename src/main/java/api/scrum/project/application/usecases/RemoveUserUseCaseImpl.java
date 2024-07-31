package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.ports.in.users.RemoveUserUseCase;
import api.scrum.project.domain.ports.in.users.UsersRequestDTO;
import api.scrum.project.domain.ports.in.users.UsersResponseDTO;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.user.domain.model.UserPublic;

public class RemoveUserUseCaseImpl implements RemoveUserUseCase {

    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;

    public RemoveUserUseCaseImpl(RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public UsersResponseDTO removeUser(UsersRequestDTO requestDTO) {
        
        RelationUserProject relation = this.relationUserProjectRepositoryPort.findByUserAndProjectId(requestDTO.getUserId(), requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "User not in project", "The user you are trying to remove is not a participant in the project"));
        
        relationUserProjectRepositoryPort.delete(relation);

        List<UserPublic> users = this.relationUserProjectRepositoryPort.findUsersByProjectId(requestDTO.getProjectId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"))
            .stream().map(user -> this.modelMapper.map(user, UserPublic.class)).toList();

        UsersResponseDTO responseDTO = this.modelMapper.map(relation.getProject(), UsersResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
