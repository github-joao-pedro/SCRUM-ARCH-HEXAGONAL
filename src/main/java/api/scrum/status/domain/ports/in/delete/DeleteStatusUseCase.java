package api.scrum.status.domain.ports.in.delete;

public interface DeleteStatusUseCase {
    DeleteStatusResponseDTO deleteStatus(DeleteStatusRequestDTO requestDTO);
}
