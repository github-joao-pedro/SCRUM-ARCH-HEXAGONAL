package api.scrum.user.domain.ports.in.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequestDTO {
    private String nickname;
    private String email;
    private String password;
    private String name;
    private String profilePicture;
}
