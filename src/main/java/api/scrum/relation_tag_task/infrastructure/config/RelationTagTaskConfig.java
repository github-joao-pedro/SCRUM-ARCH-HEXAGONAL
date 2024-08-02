package api.scrum.relation_tag_task.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.relation_tag_task.domain.ports.out.RelationTagTaskRepositoryPort;
import api.scrum.relation_tag_task.infrastructure.adapter.JpaRelationTagTaskAdapter;
import api.scrum.relation_tag_task.infrastructure.repositories.JpaRelationTagTaskRepository;

@Configuration
public class RelationTagTaskConfig {
    
    @Bean
    public RelationTagTaskRepositoryPort relationTagTaskRepositoryPort(JpaRelationTagTaskRepository jpaRelationTagTaskRepository, ModelMapper modelMapper) {
        return new JpaRelationTagTaskAdapter(jpaRelationTagTaskRepository, modelMapper);
    }
}
