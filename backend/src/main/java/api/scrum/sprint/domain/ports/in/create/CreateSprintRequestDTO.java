package api.scrum.sprint.domain.ports.in.create;

import java.util.Date;
import java.util.UUID;

import api.scrum.sprint.domain.model.EnumStatusSprint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSprintRequestDTO {
    private UUID projectId;
    private String name;
    private Date startDate;
    private Date endDate;
    private EnumStatusSprint status;
}
