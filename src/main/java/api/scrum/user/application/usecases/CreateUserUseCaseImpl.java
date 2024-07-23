package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.create.CreateUserRequestDTO;
import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import api.scrum.user.domain.ports.in.create.CreateUserUseCase;
import api.scrum.user.domain.ports.out.UserRepository;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    
    public CreateUserUseCaseImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        // TODO: Regras de negócio
        User user = this.modelMapper.map(requestDTO, User.class);
        User userSaved = this.userRepository.save(user);
        return this.modelMapper.map(userSaved, CreateUserResponseDTO.class);
    }
    
}
