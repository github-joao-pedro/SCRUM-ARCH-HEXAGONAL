package api.scrum.task.domain.model;

import java.util.Date;
import java.util.UUID;

import api.scrum.backlog.domain.model.Backlog;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.status.domain.model.Status;
import api.scrum.tag.domain.model.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private UUID id;
    private Backlog backlog;
    private Sprint sprint;
    private Status status;
    private Tag tag;
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateClosed;
    private EnumTaskPriority priority;
}
