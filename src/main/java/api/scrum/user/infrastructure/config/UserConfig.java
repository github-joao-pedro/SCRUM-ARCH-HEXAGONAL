package api.scrum.user.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import api.scrum.user.application.services.UserService;
import api.scrum.user.application.usecases.CreateUserUseCaseImpl;
import api.scrum.user.application.usecases.DeleteUserUseCaseImpl;
import api.scrum.user.application.usecases.ReadUserUseCaseImpl;
import api.scrum.user.application.usecases.UpdateUserUseCaseImpl;
import api.scrum.user.domain.ports.out.BCryptPasswordPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;
import api.scrum.user.infrastructure.adapter.BCryptPasswordAdapter;
import api.scrum.user.infrastructure.adapter.JpaUserRepositoryAdapter;
import api.scrum.user.infrastructure.repositories.JpaUserRepository;

@Configuration
public class UserConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserRepositoryPort userRepository(JpaUserRepository jpaUserRepository, ModelMapper modelMapper) {
        return new JpaUserRepositoryAdapter(jpaUserRepository, modelMapper);
    }

    @Bean
    public BCryptPasswordPort bCryptPassword(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new BCryptPasswordAdapter(bCryptPasswordEncoder);
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepository, ModelMapper modelMapper, BCryptPasswordPort bCryptPassword) {
        return new UserService(
            new CreateUserUseCaseImpl(userRepository,modelMapper,bCryptPassword),
            new DeleteUserUseCaseImpl(userRepository,modelMapper),
            new ReadUserUseCaseImpl(userRepository,modelMapper),
            new UpdateUserUseCaseImpl(userRepository,modelMapper)
        );
    }
}
