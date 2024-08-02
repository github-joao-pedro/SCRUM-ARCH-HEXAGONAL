package api.scrum.backlog.domain.model;

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
public class Backlog {
    private UUID id;
    private Project project;
}
