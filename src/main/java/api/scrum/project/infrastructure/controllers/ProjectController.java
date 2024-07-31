package api.scrum.project.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.project.application.services.ProjectService;
import api.scrum.project.domain.ports.in.create.CreateProjectRequestDTO;
import api.scrum.project.domain.ports.in.create.CreateProjectResponseDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectRequestDTO;
import api.scrum.project.domain.ports.in.delete.DeleteProjectResponseDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectRequestDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectResponseDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectRequestDTO;
import api.scrum.project.domain.ports.in.update.UpdateProjectResponseDTO;
import api.scrum.project.domain.ports.in.users.UsersRequestDTO;
import api.scrum.project.domain.ports.in.users.UsersResponseDTO;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public CreateProjectResponseDTO create(@RequestBody CreateProjectRequestDTO requestDTO) {
        return this.projectService.createProject(requestDTO);
    }
    @DeleteMapping
    public DeleteProjectResponseDTO delete(@RequestParam(name = "id") UUID id) {
        DeleteProjectRequestDTO requestDTO = DeleteProjectRequestDTO.builder()
            .id(id)
            .build();
        return this.projectService.deleteProject(requestDTO);
    }
    @GetMapping
    public ReadProjectResponseDTO read(@RequestParam(name = "id") UUID id) {
        return this.projectService.readProject(new ReadProjectRequestDTO(id));
    }
    @PutMapping
    public UpdateProjectResponseDTO update(@RequestBody UpdateProjectRequestDTO requestDTO) {
        return this.projectService.updateProject(requestDTO);
    }
    @PostMapping("/user")
    public UsersResponseDTO appendUser(@RequestBody UsersRequestDTO requestDTO) {
        return this.projectService.appendUser(requestDTO);
    }
    @DeleteMapping("/user")
    public UsersResponseDTO removeUser(@RequestBody UsersRequestDTO requestDTO) {
        return this.projectService.removeUser(requestDTO);
    }
    
}
