package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.model.UserPublic;
import api.scrum.project.domain.ports.in.create.CreateProjectRequestDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectResponseDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final RelationUserProjectRepositoryPort relationUserProjectRepositoryPort;
    private final ModelMapper modelMapper;


    public CreateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, UserRepositoryPort userRepositoryPort,
            RelationUserProjectRepositoryPort relationUserProjectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.relationUserProjectRepositoryPort = relationUserProjectRepositoryPort;
        this.modelMapper = modelMapper;
    }


    @Override
    public CreateProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO) {

        if (requestDTO.getRole() == null) {
            throw new ApplicationException(400, "Invalid input", "The 'role' parameter is required and cannot be null");
        }

        User existingUser = this.userRepositoryPort.findById(requestDTO.getUserId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to add does not exist"));

        Project project = this.modelMapper.map(requestDTO, Project.class);
        Project savedProject = this.projectRepositoryPort.save(project);

        RelationUserProject newRelation = RelationUserProject.builder().user(existingUser).project(savedProject).role(requestDTO.getRole()).build();
        this.relationUserProjectRepositoryPort.save(newRelation);

        List<RelationUserProject> relations = this.relationUserProjectRepositoryPort.findByProjectId(project.getId())
                .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));
        List<UserPublic> users = relations.stream().map(relation -> {
            UserPublic userPublic = this.modelMapper.map(relation.getUser(), UserPublic.class);
            userPublic.setRole(relation.getRole());
            return userPublic;
        }).toList();
        
        CreateProjectResponseDTO responseDTO = this.modelMapper.map(savedProject, CreateProjectResponseDTO.class);
        responseDTO.setUsers(users);
        return responseDTO;
    }
    
}
