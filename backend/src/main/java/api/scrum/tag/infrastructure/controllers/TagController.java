package api.scrum.tag.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.tag.application.service.TagService;
import api.scrum.tag.domain.ports.in.create.CreateTagRequestDTO;
import api.scrum.tag.domain.ports.in.create.CreateTagResponseDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagRequestDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagResponseDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagRequestDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagResponseDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagRequestDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagResponseDTO;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    @PostMapping
    public CreateTagResponseDTO create(@RequestBody CreateTagRequestDTO requestDTO) {
        return this.tagService.createTag(requestDTO);
    }
    @DeleteMapping
    public DeleteTagResponseDTO delete(@RequestParam(name = "id") UUID id) {
        return this.tagService.deleteTag(new DeleteTagRequestDTO(id));
    }
    @GetMapping
    public ReadTagResponseDTO read(
        @RequestParam(name = "id") UUID id,
        @RequestParam(name = "projectId") UUID projectId
    ) {
        return this.tagService.readTag(new ReadTagRequestDTO(id, projectId));
    }
    @PutMapping
    public UpdateTagResponseDTO update(@RequestBody UpdateTagRequestDTO requestDTO) {
        return this.tagService.updateTag(requestDTO);
    }
}
