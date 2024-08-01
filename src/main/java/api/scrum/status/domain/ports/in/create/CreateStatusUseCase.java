package api.scrum.status.domain.ports.in.create;

public interface CreateStatusUseCase {
    CreateStatusResponseDTO createStatus(CreateStatusRequestDTO requestDTO);
}
