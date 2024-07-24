package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.create.CreateUserRequestDTO;
import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import api.scrum.user.domain.ports.in.create.CreateUserUseCase;
import api.scrum.user.domain.ports.out.BCryptPasswordPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordPort bCryptPasswordPort;
    private final ModelMapper modelMapper;

    public CreateUserUseCaseImpl(
        UserRepositoryPort userRepositoryPort,
        ModelMapper modelMapper,
        BCryptPasswordPort bCryptPasswordPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.bCryptPasswordPort = bCryptPasswordPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {

        if (this.userRepositoryPort.findByNickname(requestDTO.getNickname()).isPresent()) {
            throw new ApplicationException(409, "Invalid nickname", "User nicknames are unique, use another nickname");
        }
        if (this.userRepositoryPort.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new ApplicationException(409, "Invalid email", "User email are unique, use another email");
        }
        if (requestDTO.getPassword().length() < 8) {
            throw new ApplicationException(400, "Invalid password", "Password must contain at least 8 characters");
        }

        User user = this.modelMapper.map(requestDTO, User.class);
        user.setPassword(this.bCryptPasswordPort.encode(requestDTO.getPassword()));

        User userSaved = this.userRepositoryPort.save(user);
        return this.modelMapper.map(userSaved, CreateUserResponseDTO.class);
    }
    
}
