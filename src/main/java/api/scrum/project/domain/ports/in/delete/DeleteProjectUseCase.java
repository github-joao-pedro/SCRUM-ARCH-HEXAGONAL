package api.scrum.project.domain.ports.in.delete;

public interface DeleteProjectUseCase {
    DeleteProjectResponseDTO deleteProject(DeleteProjectRequestDTO requestDTO);
}
