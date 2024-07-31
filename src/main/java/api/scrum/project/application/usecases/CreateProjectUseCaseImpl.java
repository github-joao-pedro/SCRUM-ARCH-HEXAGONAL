package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
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
        User existingUser = this.userRepositoryPort.findById(requestDTO.getUserId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to add does not exist"));

        Project project = this.modelMapper.map(requestDTO, Project.class);
        Project savedProject = this.projectRepositoryPort.save(project);

        RelationUserProject relation = RelationUserProject.builder().user(existingUser).project(savedProject).role("ADM").build();
        RelationUserProject savedRelation = this.relationUserProjectRepositoryPort.save(relation);

        CreateProjectResponseDTO responseDTO = this.modelMapper.map(savedProject, CreateProjectResponseDTO.class);
        responseDTO.setUsers(List.of(savedRelation.getUser()));
        return responseDTO;
    }
    
}
