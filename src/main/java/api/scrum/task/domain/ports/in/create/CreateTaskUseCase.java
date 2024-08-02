package api.scrum.task.domain.ports.in.create;

public interface CreateTaskUseCase {
    CreateTaskResponseDTO createTask(CreateTaskRequestDTO requestDTO);
}
