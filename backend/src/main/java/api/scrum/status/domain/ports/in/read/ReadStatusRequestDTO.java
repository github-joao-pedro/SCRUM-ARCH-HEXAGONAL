package api.scrum.status.domain.ports.in.read;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadStatusRequestDTO {
    private UUID id;
    private UUID projectId;
}
