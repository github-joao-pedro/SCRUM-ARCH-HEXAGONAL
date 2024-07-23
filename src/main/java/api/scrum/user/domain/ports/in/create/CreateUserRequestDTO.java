package api.scrum.user.domain.ports.in.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {
    private String nickname;
    private String email;
    private String password;
    private String name;
    private String profilePicture;
}
