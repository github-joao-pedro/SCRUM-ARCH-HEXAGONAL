package api.scrum.project.domain.ports.in.create;

public interface CreateProjectUseCase {
    CreateProjectResponseDTO createProject(CreateProjectRequestDTO requestDTO);
}
