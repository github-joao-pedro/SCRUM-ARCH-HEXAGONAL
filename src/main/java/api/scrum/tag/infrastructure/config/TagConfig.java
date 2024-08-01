package api.scrum.tag.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.tag.application.service.TagService;
import api.scrum.tag.application.usecases.CreateTagUseCaseImpl;
import api.scrum.tag.application.usecases.DeleteTagUseCaseImpl;
import api.scrum.tag.application.usecases.ReadTagUseCaseImpl;
import api.scrum.tag.application.usecases.UpdateTagUseCaseImpl;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;
import api.scrum.tag.infrastructure.adapter.JpaTagRepositoryAdapter;
import api.scrum.tag.infrastructure.repositories.JpaTagRepository;

@Configuration
public class TagConfig {
    
    @Bean
    public TagRepositoryPort tagRepositoryPort(JpaTagRepository jpaTagRepository, ModelMapper modelMapper) {
        return new JpaTagRepositoryAdapter(jpaTagRepository, modelMapper);
    }

    @Bean
    public TagService tagService(TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        return new TagService(
            new CreateTagUseCaseImpl(tagRepositoryPort, modelMapper), 
            new DeleteTagUseCaseImpl(tagRepositoryPort, modelMapper), 
            new ReadTagUseCaseImpl(tagRepositoryPort, modelMapper),
            new UpdateTagUseCaseImpl(tagRepositoryPort, modelMapper)
        );
    }
}
