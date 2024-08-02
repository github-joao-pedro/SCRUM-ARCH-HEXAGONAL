package api.scrum.tag.domain.ports.in.update;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTagResponseDTO {
    private UUID id;
    private UUID projectId;
    private String name;
}
