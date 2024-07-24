package api.scrum.authenticate.infrastructure.adapter;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;

import api.scrum.authenticate.domain.ports.in.validate.ValidateJwtDTO;
import api.scrum.authenticate.domain.ports.out.AuthJwtPort;
import api.scrum.exceptions.domain.ApplicationException;

public class AuthJwtAdapter implements AuthJwtPort {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    
    public AuthJwtAdapter(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }
    @Override
    public String generateToken(String nickname, String password, Instant now, Long expiresIn) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("scrum-api")
            .subject(nickname)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .build();
            
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    @Override
    public ValidateJwtDTO readToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Instant now = Instant.now();
            Instant issuedAt = jwt.getIssuedAt();
            Instant expiresAt = jwt.getExpiresAt();
            if (expiresAt != null && now.isAfter(expiresAt)) {
                throw new ApplicationException(401, "Token expired", "The token has expired. Please login again.");
            }
            if (issuedAt == null || expiresAt == null) {
                throw new ApplicationException(500, "Failed process", "Failed to process token");
            }
            return new ValidateJwtDTO(jwt.getSubject(),issuedAt.toString(),expiresAt.toString());
        } catch (JwtException e) {
            throw new ApplicationException(401, "Invalid token", "The token is invalid or malformed.");
        }
    }

    
}
