package api.scrum.task.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.task.domain.ports.in.update.UpdateTaskRequestDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskResponseDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskUseCase;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;

public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, ModelMapper modelMapper) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public UpdateTaskResponseDTO updateTask(UpdateTaskRequestDTO requestDTO) {
        return null;
    }
    
}
