package api.scrum.project.domain.ports.in.update;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRequestDTO {
    private UUID id;
    private String name;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private Integer sprintDuration;
    private Integer sprintAmmount;
}
