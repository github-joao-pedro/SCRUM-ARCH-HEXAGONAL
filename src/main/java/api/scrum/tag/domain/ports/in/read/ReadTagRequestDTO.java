package api.scrum.tag.domain.ports.in.read;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadTagRequestDTO {
    private UUID id;
    private UUID projectId;
}
