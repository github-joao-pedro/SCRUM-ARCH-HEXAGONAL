package api.scrum.status.domain.ports.in.read;

import java.util.List;

import api.scrum.project.domain.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadStatusResponseDTO {
    private List<StatusDTO> status;
    private Project project;
}
