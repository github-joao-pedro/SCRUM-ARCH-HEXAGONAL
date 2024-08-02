package api.scrum.status.domain.ports.in.update;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusRequestDTO {
    private UUID id;
    private UUID projectId;
    private String name;
}
