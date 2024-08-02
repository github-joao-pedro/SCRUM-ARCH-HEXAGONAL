package api.scrum.relation_user_project.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.relation_user_project.domain.ports.out.RelationUserProjectRepositoryPort;
import api.scrum.relation_user_project.infrastructure.adapter.JpaRelationUserProjectAdapter;
import api.scrum.relation_user_project.infrastructure.repositories.JpaRelationUserProjectRepository;

@Configuration
public class RelationUserProjectConfig {
    
    @Bean
    public RelationUserProjectRepositoryPort relationUserProjectRepositoryPort(JpaRelationUserProjectRepository relationUserProjectRepository, ModelMapper modelMapper) {
        return new JpaRelationUserProjectAdapter(relationUserProjectRepository, modelMapper);
    }
}
