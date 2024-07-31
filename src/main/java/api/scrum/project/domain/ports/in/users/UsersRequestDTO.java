package api.scrum.project.domain.ports.in.users;

import java.util.UUID;

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
    private String role;
}

