package api.scrum.project.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.project.application.services.ProjectService;
import api.scrum.project.application.usecases.CreateProjectUseCaseImpl;
import api.scrum.project.application.usecases.DeleteProjectUseCaseImpl;
import api.scrum.project.application.usecases.ReadProjectUseCaseImpl;
import api.scrum.project.application.usecases.UpdateProjectUseCaseImpl;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.project.infrastructure.adapter.JpaProjectRepositoryAdapter;
import api.scrum.project.infrastructure.repositories.JpaProjectRepository;

@Configuration
public class ProjectConfig {
    
    @Bean
    public ProjectRepositoryPort projectRepositoryPort(JpaProjectRepository jpaProjectRepository, ModelMapper modelMapper) {
        return new JpaProjectRepositoryAdapter(jpaProjectRepository, modelMapper);
    }
    @Bean
    public ProjectService projectService(ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        return new ProjectService(
            new CreateProjectUseCaseImpl(projectRepositoryPort, modelMapper), 
            new DeleteProjectUseCaseImpl(projectRepositoryPort, modelMapper), 
            new ReadProjectUseCaseImpl(projectRepositoryPort, modelMapper), 
            new UpdateProjectUseCaseImpl(projectRepositoryPort, modelMapper));
    }
}
