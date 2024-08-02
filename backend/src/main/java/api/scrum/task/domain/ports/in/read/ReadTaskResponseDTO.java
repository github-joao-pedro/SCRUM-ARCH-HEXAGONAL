package api.scrum.task.domain.ports.in.read;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadTaskResponseDTO {
    private List<TaskDTO> tasks;
}
