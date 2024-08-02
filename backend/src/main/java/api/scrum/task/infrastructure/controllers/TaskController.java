package api.scrum.task.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.task.application.service.TaskService;
import api.scrum.task.domain.model.EnumTaskPriority;
import api.scrum.task.domain.ports.in.create.CreateTaskRequestDTO;
import api.scrum.task.domain.ports.in.create.CreateTaskResponseDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskRequestDTO;
import api.scrum.task.domain.ports.in.delete.DeleteTaskResponseDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskRequestDTO;
import api.scrum.task.domain.ports.in.read.ReadTaskResponseDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskRequestDTO;
import api.scrum.task.domain.ports.in.update.UpdateTaskResponseDTO;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public CreateTaskResponseDTO create(@RequestBody CreateTaskRequestDTO requestDTO) {
        return this.taskService.createTask(requestDTO);
    }

    @DeleteMapping
    public DeleteTaskResponseDTO delete(@RequestParam(name = "id") UUID id) {
        return this.taskService.deleteTask(new DeleteTaskRequestDTO(id));
    }
    @GetMapping
    public ReadTaskResponseDTO read(
        @RequestParam(required = false, name = "id") UUID id,
        @RequestParam(required = false, name = "backlogId") UUID backlogId,
        @RequestParam(required = false, name = "sprintId") UUID sprintId,
        @RequestParam(required = false, name = "statusId") UUID statusId,
        @RequestParam(required = false, name = "tagId") UUID tagId,
        @RequestParam(required = false, name = "priority") EnumTaskPriority priority) {
        return this.taskService.readTask(new ReadTaskRequestDTO(id,backlogId,sprintId,statusId,tagId,priority));
    }
    @PutMapping
    public UpdateTaskResponseDTO update(@RequestBody UpdateTaskRequestDTO requestDTO) {
        return this.taskService.updateTask(requestDTO);
    }
}
