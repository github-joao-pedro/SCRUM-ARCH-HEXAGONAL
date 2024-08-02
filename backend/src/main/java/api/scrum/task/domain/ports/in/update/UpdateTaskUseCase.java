package api.scrum.task.domain.ports.in.update;

public interface UpdateTaskUseCase {
    UpdateTaskResponseDTO updateTask(UpdateTaskRequestDTO requestDTO);
}
