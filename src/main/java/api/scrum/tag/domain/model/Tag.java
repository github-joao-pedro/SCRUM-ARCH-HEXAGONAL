package api.scrum.tag.domain.model;

import java.util.UUID;

import api.scrum.project.domain.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    private UUID id;
    private Project project;
    private String name; 
}
