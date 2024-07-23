package api.scrum.user.domain.ports.in.delete;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteUserRequestDTO {
    private UUID id;
}
