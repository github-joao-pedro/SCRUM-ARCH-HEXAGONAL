package api.scrum.sprint.domain.ports.in.read;

public interface ReadSprintUseCase {
    ReadSprintResponseDTO readSprint(ReadSprintRequestDTO requestDTO);
}
