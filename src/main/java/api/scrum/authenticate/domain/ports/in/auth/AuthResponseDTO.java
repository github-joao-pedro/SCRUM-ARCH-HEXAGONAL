package api.scrum.authenticate.domain.ports.in.auth;

import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String tokenType;
    private Long expiresIn;
    private CreateUserResponseDTO user;
    private String accessToken;
}
