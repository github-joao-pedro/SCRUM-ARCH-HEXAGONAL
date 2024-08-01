package api.scrum.backlog.domain.ports.in.read;

public interface ReadBacklogUseCase {
    ReadBacklogResponseDTO readBacklog(ReadBacklogRequestDTO requestDTO);
}
