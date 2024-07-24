package api.scrum.authenticate.application.usecase;

import java.time.Instant;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import api.scrum.authenticate.domain.ports.in.auth.AuthRequestDTO;
import api.scrum.authenticate.domain.ports.in.auth.AuthResponseDTO;
import api.scrum.authenticate.domain.ports.in.auth.AuthUseCase;
import api.scrum.authenticate.domain.ports.out.AuthJwtPort;
import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import api.scrum.user.domain.ports.out.BCryptPasswordPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class AuthUseCaseImpl implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final BCryptPasswordPort bCryptPassword;
    private final AuthJwtPort jwtPort;
    private final ModelMapper modelMapper;

    public AuthUseCaseImpl(UserRepositoryPort userRepository, BCryptPasswordPort bCryptPassword, AuthJwtPort jwtPort,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPassword = bCryptPassword;
        this.jwtPort = jwtPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthResponseDTO auth(AuthRequestDTO requestDTO) {
        Optional<User> user = this.userRepository.findByNickname(requestDTO.getNickname());
        if (user.isEmpty() || !bCryptPassword.matches(requestDTO.getPassword(), user.get().getPassword())) {
            throw new ApplicationException(401, "Invalid credentials", "The nickname or password you entered is incorrect");
        }
        
        Instant now = Instant.now();
        Long expiresIn = 3600L;

        return new AuthResponseDTO(
            "Bearer",
            now.plusSeconds(expiresIn).toEpochMilli(),
            this.modelMapper.map(user.get(), CreateUserResponseDTO.class),
            this.jwtPort.generateToken(user.get().getNickname(),user.get().getPassword(),now,expiresIn)
        );
    }
    
}
