package api.scrum.project.infrastructure.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;

    @Column
    private Date dateFrom;
    
    @Column
    private Date dateTo;
    
    @Column
    private Integer sprintDuration;
    
    @Column
    private Integer sprintAmmount;
}
