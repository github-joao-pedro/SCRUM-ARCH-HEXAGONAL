package api.scrum.project.domain.ports.in.create;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequestDTO {
    private String name;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private Integer sprintDuration;
    private Integer sprintAmmount;
}
