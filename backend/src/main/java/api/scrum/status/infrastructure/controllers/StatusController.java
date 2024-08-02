package api.scrum.status.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.status.application.service.StatusService;
import api.scrum.status.domain.ports.in.create.CreateStatusRequestDTO;
import api.scrum.status.domain.ports.in.create.CreateStatusResponseDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusRequestDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusResponseDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusRequestDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusResponseDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusRequestDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusResponseDTO;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
    
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }
    
    @PostMapping
    public CreateStatusResponseDTO create(@RequestBody CreateStatusRequestDTO requestDTO) {
        return this.statusService.createStatus(requestDTO);
    }

    @DeleteMapping
    public DeleteStatusResponseDTO delete(@RequestParam(name = "id") UUID id) {
        return this.statusService.deleteStatus(new DeleteStatusRequestDTO(id));
    }

    @GetMapping
    public ReadStatusResponseDTO read(
        @RequestParam(name = "id", required = false) UUID id,
        @RequestParam(name = "projectId", required = false) UUID projectId
    ) {
        return this.statusService.readStatus(new ReadStatusRequestDTO(id, projectId));
    }

    @PutMapping
    public UpdateStatusResponseDTO update(@RequestBody UpdateStatusRequestDTO requestDTO) {
        return this.statusService.updateStatus(requestDTO);
    }
}
