package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.delete.DeleteUserRequestDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserResponseDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserUseCase;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    
    private final UserRepositoryPort userRepository;
    private final ModelMapper modelMapper;

    public DeleteUserUseCaseImpl(UserRepositoryPort userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO requestDTO) {
        User user = this.userRepository.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to delete does not exist"));
        this.userRepository.delete(user);
        return this.modelMapper.map(user, DeleteUserResponseDTO.class);
    }
}
