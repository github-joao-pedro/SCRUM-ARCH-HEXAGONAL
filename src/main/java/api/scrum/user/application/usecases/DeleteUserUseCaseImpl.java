package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.delete.DeleteUserRequestDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserResponseDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserUseCase;
import api.scrum.user.domain.ports.out.UserRepository;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public DeleteUserUseCaseImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO requestDTO) {
        // TODO: Regras de negócio
        User user = this.userRepository.findById(requestDTO.getId()).get();
        this.userRepository.delete(user);
        return this.modelMapper.map(user, DeleteUserResponseDTO.class);
    }
}
