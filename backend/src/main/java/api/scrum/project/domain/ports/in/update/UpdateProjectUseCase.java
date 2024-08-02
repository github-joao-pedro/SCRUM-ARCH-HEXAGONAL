package api.scrum.project.domain.ports.in.update;

public interface UpdateProjectUseCase {
    UpdateProjectResponseDTO updateProject(UpdateProjectRequestDTO requestDTO);
}
