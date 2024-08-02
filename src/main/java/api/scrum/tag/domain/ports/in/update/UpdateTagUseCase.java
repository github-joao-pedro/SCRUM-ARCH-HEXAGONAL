package api.scrum.tag.domain.ports.in.update;

public interface UpdateTagUseCase {
    UpdateTagResponseDTO updateTag(UpdateTagRequestDTO requestDTO);
}
