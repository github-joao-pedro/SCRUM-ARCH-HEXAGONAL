package api.scrum.tag.domain.ports.in.read;

public interface ReadTagUseCase {
    ReadTagResponseDTO readTag(ReadTagRequestDTO requestDTO);
}
