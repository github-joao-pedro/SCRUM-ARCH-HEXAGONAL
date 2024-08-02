package api.scrum.project.domain.ports.in.read;

public interface ReadProjectUseCase {
    ReadProjectResponseDTO readProject(ReadProjectRequestDTO requestDTO);
}
