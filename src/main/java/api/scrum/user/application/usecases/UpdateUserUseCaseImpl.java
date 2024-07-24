package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.update.UpdateUserReponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserRequestDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserUseCase;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    
    private final UserRepositoryPort userRepository;
    private final ModelMapper modelMapper;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UpdateUserReponseDTO updateUser(UpdateUserRequestDTO requestDTO) {
        User user = this.userRepository.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to delete does not exist"));
        User userSaved = this.userRepository.save(user);
        return this.modelMapper.map(userSaved, UpdateUserReponseDTO.class);
    }
}
