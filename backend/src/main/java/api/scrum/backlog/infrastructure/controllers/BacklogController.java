package api.scrum.backlog.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.backlog.application.services.BacklogService;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogRequestDTO;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogResponseDTO;

@RestController
@RequestMapping("/api/v1/backlog")
public class BacklogController {
    
    private final BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping
    public ReadBacklogResponseDTO read(
        @RequestParam(required = false, name = "id") UUID id,
        @RequestParam(required = false, name = "project_id") UUID projectId
    ) {
        return this.backlogService.readBacklog(new ReadBacklogRequestDTO(id,projectId));
    }
}
