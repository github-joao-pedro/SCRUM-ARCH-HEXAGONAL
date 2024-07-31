package api.scrum.sprint.domain.ports.in.update;

public interface UpdateSprintUseCase {
    UpdateSprintResponseDTO updateSprint(UpdateSprintRequestDTO requestDTO);
}
