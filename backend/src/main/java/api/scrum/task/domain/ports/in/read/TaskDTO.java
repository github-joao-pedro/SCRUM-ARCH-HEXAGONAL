package api.scrum.task.domain.ports.in.read;

import java.util.Date;

import api.scrum.task.domain.model.EnumTaskPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateClosed;
    private EnumTaskPriority priority;
}
