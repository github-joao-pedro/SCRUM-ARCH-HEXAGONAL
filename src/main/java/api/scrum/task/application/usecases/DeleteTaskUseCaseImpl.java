package api.scrum.task.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.task.domain.model.Task;
import api.scrum.task.domain.ports.in.delete.DeleteTaskRequestDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskResponseDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskUseCase;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;

public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ModelMapper modelMapper;
    
    public DeleteTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, ModelMapper modelMapper) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteTaskResponseDTO deleteTask(DeleteTaskRequestDTO requestDTO) {
        Task task = this.taskRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Task not found", ""));
        this.taskRepositoryPort.delete(task);
        return(this.modelMapper.map(task, DeleteTaskResponseDTO.class));
    }
    
}
