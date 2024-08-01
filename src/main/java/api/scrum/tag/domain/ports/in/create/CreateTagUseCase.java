package api.scrum.tag.domain.ports.in.create;

public interface CreateTagUseCase {
    CreateTagResponseDTO createStatus(CreateTagRequestDTO requestDTO);
}
