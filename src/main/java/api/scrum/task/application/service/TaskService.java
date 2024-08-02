package api.scrum.task.application.service;

import api.scrum.task.domain.ports.in.create.CreateTaskRequestDTO;
import api.scrum.task.domain.ports.in.create.CreateTaskResponseDTO;
import api.scrum.task.domain.ports.in.create.CreateTaskUseCase;
import api.scrum.task.domain.ports.in.delete.DeleteTaskRequestDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskResponseDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskUseCase;
import api.scrum.task.domain.ports.in.read.ReadTaskRequestDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskResponseDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskUseCase;
import api.scrum.task.domain.ports.in.update.UpdateTaskRequestDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskResponseDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskUseCase;

public class TaskService implements 
    CreateTaskUseCase,
    DeleteTaskUseCase,
    ReadTaskUseCase,
    UpdateTaskUseCase {

    private final CreateTaskUseCase createTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final ReadTaskUseCase readTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public TaskService(CreateTaskUseCase createTaskUseCase, DeleteTaskUseCase deleteTaskUseCase,
            ReadTaskUseCase readTaskUseCase, UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.readTaskUseCase = readTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }
    @Override
    public CreateTaskResponseDTO createTask(CreateTaskRequestDTO requestDTO) {
        return this.createTaskUseCase.createTask(requestDTO);
    }
    @Override
    public DeleteTaskResponseDTO deleteTask(DeleteTaskRequestDTO requestDTO) {
        return this.deleteTaskUseCase.deleteTask(requestDTO);
    }
    @Override
    public ReadTaskResponseDTO readTask(ReadTaskRequestDTO requestDTO) {
        return this.readTaskUseCase.readTask(requestDTO);
    }
    @Override
    public UpdateTaskResponseDTO updateTask(UpdateTaskRequestDTO requestDTO) {
        return this.updateTaskUseCase.updateTask(requestDTO);
    }

    
    
}
