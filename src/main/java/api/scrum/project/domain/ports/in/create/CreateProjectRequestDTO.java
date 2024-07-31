package api.scrum.project.domain.ports.in.create;

import java.util.Date;
import java.util.UUID;

import api.scrum.relation_user_project.domain.model.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequestDTO {
    private UUID userId;
    private EnumRole role;
    private String name;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private Integer sprintDuration;
    private Integer sprintAmmount;
}
