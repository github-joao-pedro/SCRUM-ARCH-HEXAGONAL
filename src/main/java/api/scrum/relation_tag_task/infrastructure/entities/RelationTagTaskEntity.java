package api.scrum.relation_tag_task.infrastructure.entities;

import java.util.UUID;

import api.scrum.tag.infrastructure.entities.TagEntity;
import api.scrum.task.infrastructure.entities.TaskEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "relation_tag_task",
    uniqueConstraints = @UniqueConstraint(columnNames = {"tag_id", "task_id"}))
public class RelationTagTaskEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
}
