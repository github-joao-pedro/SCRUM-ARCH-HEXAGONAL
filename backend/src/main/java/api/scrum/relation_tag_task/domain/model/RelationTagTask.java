package api.scrum.relation_tag_task.domain.model;

import java.util.UUID;

import api.scrum.tag.domain.model.Tag;
import api.scrum.task.domain.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationTagTask {
    private UUID id;
    private Tag tag;
    private Task task;
}
