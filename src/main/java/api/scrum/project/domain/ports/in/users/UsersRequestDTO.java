package api.scrum.project.domain.ports.in.users;

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
public class UsersRequestDTO {
    private UUID userId;
    private UUID projectId;
    private EnumRole role;
}

