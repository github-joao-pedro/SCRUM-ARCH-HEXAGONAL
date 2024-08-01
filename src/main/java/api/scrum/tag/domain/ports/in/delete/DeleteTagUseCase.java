package api.scrum.tag.domain.ports.in.delete;

public interface DeleteTagUseCase {
    DeleteTagResponseDTO deleteStatus(DeleteTagRequestDTO requestDTO);
}
