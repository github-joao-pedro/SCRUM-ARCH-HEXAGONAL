package api.scrum.task.domain.ports.in.delete;

public interface DeleteTaskUseCase {
    DeleteTaskResponseDTO deleteTask(DeleteTaskRequestDTO requestDTO);
}
