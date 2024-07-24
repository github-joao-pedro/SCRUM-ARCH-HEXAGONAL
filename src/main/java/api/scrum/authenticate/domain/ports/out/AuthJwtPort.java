package api.scrum.authenticate.domain.ports.out;

import java.time.Instant;

import api.scrum.authenticate.domain.ports.in.validate.ValidateJwtDTO;

public interface AuthJwtPort {
    String generateToken(String nickname, String password, Instant now, Long expiresIn);
    ValidateJwtDTO readToken(String token);
}
