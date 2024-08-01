package api.scrum.status.domain.ports.in.update;

public interface UpdateStatusUseCase {
    UpdateStatusResponseDTO updateStatus(UpdateStatusRequestDTO requestDTO);
}
