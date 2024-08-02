package api.scrum.task.domain.ports.in.create;

import java.util.Date;
import java.util.UUID;

import api.scrum.task.domain.model.EnumTaskPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequestDTO {
    private UUID id;
    private UUID backlogId;
    private UUID sprintId;
    private UUID statusId;
    private UUID tagId;
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateClosed;
    private EnumTaskPriority priority;
}
