package api.scrum.project.domain.model;

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
public class UserPublic {
    private UUID id;
    private String nickname;
    private String email;
    private String name;
    private String profilePicture;
    private EnumRole role;
}
