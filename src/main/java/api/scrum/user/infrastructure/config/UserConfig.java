package api.scrum.user.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.user.application.services.UserService;
import api.scrum.user.application.usecases.CreateUserUseCaseImpl;
import api.scrum.user.application.usecases.DeleteUserUseCaseImpl;
import api.scrum.user.application.usecases.ReadUserUseCaseImpl;
import api.scrum.user.application.usecases.UpdateUserUseCaseImpl;
import api.scrum.user.domain.ports.out.UserRepository;
import api.scrum.user.infrastructure.repositories.JpaUserRepository;
import api.scrum.user.infrastructure.repositories.JpaUserRepositoryAdapter;

@Configuration
public class UserConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository, ModelMapper modelMapper) {
        return new JpaUserRepositoryAdapter(jpaUserRepository, modelMapper);
    }

    @Bean
    public UserService userService(UserRepository userRepository, ModelMapper modelMapper) {
        return new UserService(
            new CreateUserUseCaseImpl(userRepository,modelMapper),
            new DeleteUserUseCaseImpl(userRepository,modelMapper),
            new ReadUserUseCaseImpl(userRepository,modelMapper),
            new UpdateUserUseCaseImpl(userRepository,modelMapper)
        );
    }
}
