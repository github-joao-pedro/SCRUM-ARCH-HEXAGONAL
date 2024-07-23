package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.update.UpdateUserReponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserRequestDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserUseCase;
import api.scrum.user.domain.ports.out.UserRepository;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UpdateUserUseCaseImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UpdateUserReponseDTO updateUser(UpdateUserRequestDTO requestDTO) {
        // TODO: Regras de negócio
        User user = this.modelMapper.map(requestDTO, User.class);
        User userSaved = this.userRepository.save(user);
        return this.modelMapper.map(userSaved, UpdateUserReponseDTO.class);
    }
}
