package api.scrum.project.application.services;

import api.scrum.project.domain.ports.in.create.CreateProjectRequestDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectResponseDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectUseCase;
import api.scrum.project.domain.ports.in.delete.DeleteProjectRequestDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectResponseDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectUseCase;
import api.scrum.project.domain.ports.in.read.ReadProjectRequestDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectResponseDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectUseCase;
import api.scrum.project.domain.ports.in.update.UpdateProjectRequestDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectResponseDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectUseCase;
import api.scrum.project.domain.ports.in.users.AppendUserUseCase;
import api.scrum.project.domain.ports.in.users.RemoveUserUseCase;
import api.scrum.project.domain.ports.in.users.UpdateRoleUseCase;
import api.scrum.project.domain.ports.in.users.UsersRequestDTO;
import api.scrum.project.domain.ports.in.users.UsersResponseDTO;

public class ProjectService implements
    CreateProjectUseCase,
    DeleteProjectUseCase,
    ReadProjectUseCase,
    UpdateProjectUseCase,
    AppendUserUseCase,
    RemoveUserUseCase,
    UpdateRoleUseCase {

    private final CreateProjectUseCase createProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final ReadProjectUseCase readProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final AppendUserUseCase appendUserUseCase;
    private final RemoveUserUseCase removeUserUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;

    public ProjectService(
        CreateProjectUseCase createProjectUseCase,
        DeleteProjectUseCase deleteProjectUseCase,
        ReadProjectUseCase readProjectUseCase,
        UpdateProjectUseCase updateProjectUseCase,
        AppendUserUseCase appendUserUseCase,
        RemoveUserUseCase removeUserUseCase,
        UpdateRoleUseCase updateRoleUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
        this.readProjectUseCase = readProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        this.appendUserUseCase = appendUserUseCase;
        this.removeUserUseCase = removeUserUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
    }
    @Override
    public CreateProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO) {
        return this.createProjectUseCase.createProject(requestDTO);
    }
    @Override
    public DeleteProjectResponseDTO deleteProject(DeleteProjectRequestDTO requestDTO) {
        return this.deleteProjectUseCase.deleteProject(requestDTO);
    }
    @Override
    public ReadProjectResponseDTO readProject(ReadProjectRequestDTO requestDTO) {
        return this.readProjectUseCase.readProject(requestDTO);
    }
    @Override
    public UpdateProjectResponseDTO updateProject(UpdateProjectRequestDTO requestDTO) {
        return this.updateProjectUseCase.updateProject(requestDTO);
    }
    @Override
    public UsersResponseDTO appendUser(UsersRequestDTO requestDTO) {
        return this.appendUserUseCase.appendUser(requestDTO);
    }
    @Override
    public UsersResponseDTO removeUser(UsersRequestDTO requestDTO) {
        return this.removeUserUseCase.removeUser(requestDTO);
    }
    @Override
    public UsersResponseDTO updateRole(UsersRequestDTO requestDTO) {
        return this.updateRoleUseCase.updateRole(requestDTO);
    }
}
