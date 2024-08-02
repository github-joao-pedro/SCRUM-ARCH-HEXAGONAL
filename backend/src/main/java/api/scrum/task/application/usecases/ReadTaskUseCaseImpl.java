package api.scrum.task.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.task.domain.ports.in.read.ReadTaskRequestDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskResponseDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskUseCase;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;

public class ReadTaskUseCaseImpl implements ReadTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ModelMapper modelMapper;
    
    public ReadTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, ModelMapper modelMapper) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReadTaskResponseDTO readTask(ReadTaskRequestDTO requestDTO) {
        return null;
    }
    
}
