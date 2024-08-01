package api.scrum.status.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.status.application.service.StatusService;
import api.scrum.status.application.usecases.CreateStatusUseCaseImpl;
import api.scrum.status.application.usecases.DeleteStatusUseCaseImpl;
import api.scrum.status.application.usecases.ReadStatusUseCaseImpl;
import api.scrum.status.application.usecases.UpdateStatusUseCaseImpl;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;
import api.scrum.status.infrastructure.adapter.JpaStatusRepositoryAdapter;
import api.scrum.status.infrastructure.repositories.JpaStatusRepository;

@Configuration
public class StatusConfig {
    
    @Bean
    public StatusRepositoryPort statusRepositoryPort(JpaStatusRepository jpaStatusRepository, ModelMapper modelMapper) {
        return new JpaStatusRepositoryAdapter(jpaStatusRepository, modelMapper);
    }

    @Bean
    public StatusService statusService(StatusRepositoryPort statusRepositoryPort, ModelMapper modelMapper) {
        return new StatusService(
            new CreateStatusUseCaseImpl(statusRepositoryPort, modelMapper),
            new DeleteStatusUseCaseImpl(statusRepositoryPort, modelMapper),
            new ReadStatusUseCaseImpl(statusRepositoryPort, modelMapper),
            new UpdateStatusUseCaseImpl(statusRepositoryPort, modelMapper)
        );
    }
}
