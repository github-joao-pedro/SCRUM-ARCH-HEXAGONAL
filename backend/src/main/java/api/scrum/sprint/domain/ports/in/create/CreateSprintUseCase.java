package api.scrum.sprint.domain.ports.in.create;

public interface CreateSprintUseCase {
    CreateSprintResponseDTO createSprint(CreateSprintRequestDTO requestDTO);
}
