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

public class ProjectService implements
    CreateProjectUseCase,
    DeleteProjectUseCase,
    ReadProjectUseCase,
    UpdateProjectUseCase {

    private final CreateProjectUseCase createProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final ReadProjectUseCase readProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;

    public ProjectService(
        CreateProjectUseCase createProjectUseCase,
        DeleteProjectUseCase deleteProjectUseCase,
        ReadProjectUseCase readProjectUseCase,
        UpdateProjectUseCase updateProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
        this.readProjectUseCase = readProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
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
}
