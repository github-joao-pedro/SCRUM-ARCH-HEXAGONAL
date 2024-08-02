package api.scrum.relation_user_task.infrastructure.entities;

import java.util.UUID;

import api.scrum.task.infrastructure.entities.TaskEntity;
import api.scrum.user.infrastructure.entities.UserEntity;
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
@Table(name = "relation_uset_task",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "task_id"}))
public class RelationUserTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
}
