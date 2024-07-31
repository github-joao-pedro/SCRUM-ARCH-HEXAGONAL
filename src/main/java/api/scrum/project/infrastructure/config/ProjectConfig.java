package api.scrum.project.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.project.application.services.ProjectService;
import api.scrum.project.application.usecases.AppendUserUseCaseImpl;
import api.scrum.project.application.usecases.CreateProjectUseCaseImpl;
import api.scrum.project.application.usecases.DeleteProjectUseCaseImpl;
import api.scrum.project.application.usecases.ReadProjectUseCaseImpl;
import api.scrum.project.application.usecases.RemoveUserUseCaseImpl;
import api.scrum.project.application.usecases.UpdateProjectUseCaseImpl;
import api.scrum.project.application.usecases.UpdateRoleUseCaseImpl;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.project.infrastructure.adapter.JpaProjectRepositoryAdapter;
import api.scrum.project.infrastructure.repositories.JpaProjectRepository;
import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.relation_user_project.infrastructure.adapter.JpaRelationUserProjectAdapter;
import api.scrum.relation_user_project.infrastructure.repositories.JpaRelationUserProjectRepository;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

@Configuration
public class ProjectConfig {
    
    @Bean
    public ProjectRepositoryPort projectRepositoryPort(JpaProjectRepository jpaProjectRepository, ModelMapper modelMapper) {
        return new JpaProjectRepositoryAdapter(jpaProjectRepository, modelMapper);
    }

    @Bean
    public RelationUserProjectRepositoryPort relationUserProjectRepositoryPort(JpaRelationUserProjectRepository relationUserProjectRepository, ModelMapper modelMapper) {
        return new JpaRelationUserProjectAdapter(relationUserProjectRepository, modelMapper);
    }

    @Bean
    public ProjectService projectService(
        ProjectRepositoryPort projectRepositoryPort,
        UserRepositoryPort userRepositoryPort,
        RelationUserProjectRepositoryPort relationUserProjectRepositoryPort,
        ModelMapper modelMapper) {
        return new ProjectService(
            new CreateProjectUseCaseImpl(projectRepositoryPort, userRepositoryPort, relationUserProjectRepositoryPort, modelMapper), 
            new DeleteProjectUseCaseImpl(projectRepositoryPort, relationUserProjectRepositoryPort, modelMapper), 
            new ReadProjectUseCaseImpl(projectRepositoryPort, relationUserProjectRepositoryPort, modelMapper), 
            new UpdateProjectUseCaseImpl(projectRepositoryPort, relationUserProjectRepositoryPort, modelMapper),
            new AppendUserUseCaseImpl(projectRepositoryPort, userRepositoryPort, relationUserProjectRepositoryPort, modelMapper),
            new RemoveUserUseCaseImpl(relationUserProjectRepositoryPort, modelMapper),
            new UpdateRoleUseCaseImpl(relationUserProjectRepositoryPort, modelMapper));
    }
}
