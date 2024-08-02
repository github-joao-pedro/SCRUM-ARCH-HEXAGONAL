package api.scrum.relation_user_task.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.relation_user_task.domain.ports.out.RelationUserTaskRepositoryPort;
import api.scrum.relation_user_task.infrastructure.adapter.JpaRelationUserTaskAdapter;
import api.scrum.relation_user_task.infrastructure.repositories.JpaRelationUserTaskRepository;

@Configuration
public class RelationUserTaskConfig {
    
    @Bean
    public RelationUserTaskRepositoryPort relationUserTaskRepositoryPort(JpaRelationUserTaskRepository jpaRelationUserTaskRepository, ModelMapper modelMapper) {
        return new JpaRelationUserTaskAdapter(jpaRelationUserTaskRepository, modelMapper);
    }
}
