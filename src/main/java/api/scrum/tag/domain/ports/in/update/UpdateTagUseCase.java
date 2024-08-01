package api.scrum.tag.domain.ports.in.update;

public interface UpdateTagUseCase {
    UpdateTagResponseDTO updateStatus(UpdateTagRequestDTO requestDTO);
}
