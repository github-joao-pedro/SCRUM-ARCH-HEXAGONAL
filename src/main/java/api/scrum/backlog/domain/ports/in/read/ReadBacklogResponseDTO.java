package api.scrum.backlog.domain.ports.in.read;

import java.util.UUID;

import api.scrum.project.domain.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadBacklogResponseDTO {
    private UUID id;
    private Project project;
}
