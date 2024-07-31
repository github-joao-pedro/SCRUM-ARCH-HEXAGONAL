package api.scrum.sprint.domain.ports.in.delete;

public interface DeleteSprintUseCase {
    DeleteSprintResponseDTO deleteSprint(DeleteSprintRequestDTO requestDTO);
}
