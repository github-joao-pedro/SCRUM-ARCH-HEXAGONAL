package api.scrum.authenticate.domain.ports.in.validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateJwtDTO {
    private String subject;
    private String issuedAt;
    private String expiresAt;
}
