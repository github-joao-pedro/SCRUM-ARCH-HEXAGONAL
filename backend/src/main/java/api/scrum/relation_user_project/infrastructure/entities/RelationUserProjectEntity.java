package api.scrum.relation_user_project.infrastructure.entities;

import java.util.UUID;

import api.scrum.project.infrastructure.entities.ProjectEntity;
import api.scrum.relation_user_project.domain.model.EnumRole;
import api.scrum.user.infrastructure.entities.UserEntity;
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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "relation_user_project",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "project_id", "role"}))
public class RelationUserProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EnumRole role;
}
