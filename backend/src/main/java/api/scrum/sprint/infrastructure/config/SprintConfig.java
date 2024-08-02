package api.scrum.sprint.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.project.domain.ports.out.ProjectRepositoryPort;
import api.scrum.sprint.application.services.SprintService;
import api.scrum.sprint.application.usecases.CreateSprintUseCaseImpl;
import api.scrum.sprint.application.usecases.DeleteSprintUseCaseImpl;
import api.scrum.sprint.application.usecases.ReadSprintUseCaseImpl;
import api.scrum.sprint.application.usecases.UpdateSprintUseCaseImpl;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;
import api.scrum.sprint.infrastructure.adapter.JpaSprintAdapter;
import api.scrum.sprint.infrastructure.repositories.JpaSprintRepository;

@Configuration
public class SprintConfig {
    
    @Bean
    public SprintRepositoryPort sprintRepositoryPort(JpaSprintRepository jpaSprintRepository, ModelMapper modelMapper) {
        return new JpaSprintAdapter(jpaSprintRepository, modelMapper);
    }
    @Bean
    public SprintService sprintService(SprintRepositoryPort sprintRepositoryPort, ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        return new SprintService(
            new CreateSprintUseCaseImpl(sprintRepositoryPort, projectRepositoryPort, modelMapper),
            new DeleteSprintUseCaseImpl(sprintRepositoryPort, projectRepositoryPort, modelMapper),
            new ReadSprintUseCaseImpl(sprintRepositoryPort, modelMapper),
            new UpdateSprintUseCaseImpl(sprintRepositoryPort, modelMapper)
        );
    }
}
