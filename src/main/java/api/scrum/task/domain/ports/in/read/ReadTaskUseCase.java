package api.scrum.task.domain.ports.in.read;

public interface ReadTaskUseCase {
    ReadTaskResponseDTO readTask(ReadTaskRequestDTO requestDTO);
}
