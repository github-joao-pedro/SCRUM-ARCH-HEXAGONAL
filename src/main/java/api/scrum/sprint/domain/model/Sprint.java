package api.scrum.sprint.domain.model;

import java.util.Date;
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
public class Sprint {
    private UUID id;
    private Project project;
    private String name;
    private Date startDate;
    private Date endDate;
    private EnumStatusSprint status;
}
