package api.scrum.status.domain.ports.in.read;

public interface ReadStatusUseCase {
    ReadStatusResponseDTO readStatus(ReadStatusRequestDTO requestDTO);
}
