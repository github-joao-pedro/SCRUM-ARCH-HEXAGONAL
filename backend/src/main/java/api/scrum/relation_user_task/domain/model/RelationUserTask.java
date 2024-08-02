package api.scrum.relation_user_task.domain.model;

import java.util.UUID;

import api.scrum.task.domain.model.Task;
import api.scrum.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationUserTask {
    private UUID id;
    private User user;
    private Task task;
}
