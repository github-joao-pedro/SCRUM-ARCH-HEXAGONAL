package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.UserPublic;
import api.scrum.project.domain.ports.in.users.UpdateRoleUseCase;
import api.scrum.project.domain.ports.in.users.UsersRequestDTO;
import api.scrum.project.domain.ports.in.users.UsersResponseDTO;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;

public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateRoleUseCaseImpl(RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersResponseDTO updateRole(UsersRequestDTO requestDTO) {
        RelationUserProject relationExisting = this.relationUserProjectRepositoryPort.findByUserAndProjectId(requestDTO.getUserId(), requestDTO.getProjectId())
        .orElseThrow(() -> new ApplicationException(404, "User not in project", "The user you are trying to remove is not a participant in the project"));
        relationExisting.setRole(requestDTO.getRole());
        this.relationUserProjectRepositoryPort.save(relationExisting);

        
        List<RelationUserProject> relations = this.relationUserProjectRepositoryPort.findByProjectId(relationExisting.getProject().getId())
            .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));
        List<UserPublic> users = relations.stream().map(relation -> {
            UserPublic userPublic = this.modelMapper.map(relation.getUser(), UserPublic.class);
            userPublic.setRole(relation.getRole());
            return userPublic;
        }).toList();

        UsersResponseDTO responseDTO = this.modelMapper.map(relationExisting.getProject(), UsersResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
