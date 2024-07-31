package api.scrum.relation_user_project.domain.model;

import java.util.UUID;

import api.scrum.project.domain.model.Project;
import api.scrum.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationUserProject {
    private UUID id;
    private User user;
    private Project project;
    private String role;
}
