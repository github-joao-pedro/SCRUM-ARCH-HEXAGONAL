package api.scrum.task.infrastructure.entities;

import java.util.Date;
import java.util.UUID;

import api.scrum.backlog.infrastructure.entities.BacklogEntity;
import api.scrum.sprint.infrastructure.entities.SprintEntity;
import api.scrum.status.infrastructure.entities.StatusEntity;
import api.scrum.tag.infrastructure.entities.TagEntity;
import api.scrum.task.domain.model.EnumTaskPriority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "backlog_id", nullable = true)
    private BacklogEntity backlog;
    
    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = true)
    private SprintEntity sprint;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;
    
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    @Column
    private String title;
    
    @Column
    private String description;

    @Column
    private Date dateCreated;
    
    @Column
    private Date dateUpdated;
    
    @Column
    private Date dateClosed;
    
    @Column
    @Enumerated(value = EnumType.STRING)
    private EnumTaskPriority priority;
}
