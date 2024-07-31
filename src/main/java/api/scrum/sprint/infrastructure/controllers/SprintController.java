package api.scrum.sprint.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.sprint.application.services.SprintService;
import api.scrum.sprint.domain.ports.in.create.CreateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.create.CreateSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintResponseDTO;

@RestController
@RequestMapping("/api/v1/sprint")
public class SprintController {
    
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }
    @PostMapping
    public CreateSprintResponseDTO create(@RequestBody CreateSprintRequestDTO requestDTO) {
        return this.sprintService.createSprint(requestDTO);
    }
    @DeleteMapping
    public DeleteSprintResponseDTO delete(@RequestParam(name = "id") UUID id) {
        return this.sprintService.deleteSprint(new DeleteSprintRequestDTO(id));
    }
    @GetMapping
    public ReadSprintResponseDTO read(@RequestParam(name = "id") UUID id) {
        return this.sprintService.readSprint(new ReadSprintRequestDTO(id));
    }
    @PutMapping
    public UpdateSprintResponseDTO update(@RequestBody UpdateSprintRequestDTO requestDTO) {
        return this.sprintService.updateSprint(requestDTO);
    }
}
