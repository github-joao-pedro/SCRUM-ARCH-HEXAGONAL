package api.scrum.user.domain.ports.in.delete;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteUserResponseDTO {
    private UUID id;
    private String nickname;
    private String email;
    private String password;
    private String name;
    private String profilePicture;
}
