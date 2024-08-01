package api.scrum.tag.domain.ports.in.read;

public interface ReadTagUseCase {
    ReadTagResponseDTO readStatus(ReadTagRequestDTO requestDTO);
}
