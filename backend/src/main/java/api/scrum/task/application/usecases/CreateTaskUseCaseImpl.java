package api.scrum.task.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.backlog.domain.model.Backlog;
import api.scrum.backlog.domain.ports.out.BacklogRepositoryPort;
import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;
import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;
import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;
import api.scrum.task.domain.model.Task;
import api.scrum.task.domain.ports.in.create.CreateTaskRequestDTO;
import api.scrum.task.domain.ports.in.create.CreateTaskResponseDTO;
import api.scrum.task.domain.ports.in.create.CreateTaskUseCase;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final BacklogRepositoryPort backlogRepositoryPort;
    private final SprintRepositoryPort sprintRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final StatusRepositoryPort statusRepositoryPort;
    private final TagRepositoryPort tagRepositoryPort;
    private final ModelMapper modelMapper;
    
    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, BacklogRepositoryPort backlogRepositoryPort,
            SprintRepositoryPort sprintRepositoryPort, UserRepositoryPort userRepositoryPort,
            StatusRepositoryPort statusRepositoryPort, TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.backlogRepositoryPort = backlogRepositoryPort;
        this.sprintRepositoryPort = sprintRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.statusRepositoryPort = statusRepositoryPort;
        this.tagRepositoryPort = tagRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateTaskResponseDTO createTask(CreateTaskRequestDTO requestDTO) {
        
        Backlog backlog = this.backlogRepositoryPort.findById(requestDTO.getBacklogId())
            .orElseThrow(() -> new ApplicationException(404, "Backlog not found", ""));
        
        Sprint sprint = this.sprintRepositoryPort.findById(requestDTO.getSprintId())
            .orElseThrow(() -> new ApplicationException(404, "Sprint not found", ""));
        
        Status status = this.statusRepositoryPort.findById(requestDTO.getStatusId())
            .orElseThrow(() -> new ApplicationException(404, "Status not found", ""));
        
        Tag tag = this.tagRepositoryPort.findById(requestDTO.getTagId())
            .orElseThrow(() -> new ApplicationException(404, "Tag not found", ""));
        
        Task task = Task.builder()
        .backlog(backlog)
        .sprint(sprint)
        .status(status)
        .tag(tag)
        .title(requestDTO.getTitle())
        .description(requestDTO.getDescription())
        .dateCreated(requestDTO.getDateCreated())
        .dateUpdated(requestDTO.getDateUpdated())
        .dateClosed(requestDTO.getDateClosed())
        .priority(requestDTO.getPriority())
        .build();
        Task saved = this.taskRepositoryPort.save(task);
        return this.modelMapper.map(saved, CreateTaskResponseDTO.class);
    }
    
}
