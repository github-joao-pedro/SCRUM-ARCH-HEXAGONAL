package api.scrum.backlog.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.backlog.application.services.BacklogService;
import api.scrum.backlog.application.usecases.ReadBacklogUseCaseImpl;
import api.scrum.backlog.domain.ports.out.BacklogRepositoryPort;
import api.scrum.backlog.infrastructure.adapter.JpaBacklogAdapter;
import api.scrum.backlog.infrastructure.repositories.JpaBacklogRepository;

@Configuration
public class BacklogConfig {
    
    @Bean
    public BacklogRepositoryPort backlogRepositoryPort(JpaBacklogRepository jpaBacklogRepository, ModelMapper modelMapper) {
        return new JpaBacklogAdapter(jpaBacklogRepository, modelMapper);
    }

    @Bean
    public BacklogService backlogService(BacklogRepositoryPort backlogRepositoryPort) {
        return new BacklogService(
            new ReadBacklogUseCaseImpl(backlogRepositoryPort)
        );
    }
}
