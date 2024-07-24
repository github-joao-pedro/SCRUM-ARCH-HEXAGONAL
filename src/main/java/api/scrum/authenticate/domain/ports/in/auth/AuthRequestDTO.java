package api.scrum.authenticate.domain.ports.in.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    private String nickname;
    private String email;
    private String password;
}
