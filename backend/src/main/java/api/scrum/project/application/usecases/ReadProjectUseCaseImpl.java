package api.scrum.project.application.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.model.UserPublic;
import api.scrum.project.domain.ports.in.read.ReadProjectRequestDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectResponseDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.relation_user_project.domain.model.RelationUserProject;
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

            List<RelationUserProject> relations = this.relationUserProjectRepositoryPort.findByProjectId(project.getId())
                .orElseThrow(() -> new ApplicationException(404, "Users not found", "No users found in this project"));
            List<UserPublic> users = relations.stream().map(relation -> {
                UserPublic userPublic = this.modelMapper.map(relation.getUser(), UserPublic.class);
                userPublic.setRole(relation.getRole());
                return userPublic;
            }).toList();
            
            ReadProjectResponseDTO response = this.modelMapper.map(project, ReadProjectResponseDTO.class);
            response.setUsers(users);
            return response;
        } else {
            throw new ApplicationException(400,"Invalid request","Provide a valid 'id'");
        }
    }
    
}
