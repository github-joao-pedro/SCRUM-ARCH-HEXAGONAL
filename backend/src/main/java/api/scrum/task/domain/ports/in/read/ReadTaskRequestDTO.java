package api.scrum.task.domain.ports.in.read;

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
public class ReadTaskRequestDTO {
    private UUID id;
    private UUID backlogId;
    private UUID sprintId;
    private UUID statusId;
    private UUID tagId;
    private EnumTaskPriority priority;
}
