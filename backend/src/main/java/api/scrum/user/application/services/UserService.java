package api.scrum.user.application.services;

import api.scrum.user.domain.ports.in.create.CreateUserRequestDTO;
import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import api.scrum.user.domain.ports.in.create.CreateUserUseCase;
import api.scrum.user.domain.ports.in.delete.DeleteUserRequestDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserResponseDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserUseCase;
import api.scrum.user.domain.ports.in.read.ReadUserRequestDTO;
import api.scrum.user.domain.ports.in.read.ReadUserResponseDTO;
import api.scrum.user.domain.ports.in.read.ReadUserUseCase;
import api.scrum.user.domain.ports.in.update.UpdateUserReponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserRequestDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserUseCase;

public class UserService implements 
    CreateUserUseCase,
    DeleteUserUseCase,
    ReadUserUseCase,
    UpdateUserUseCase {
    
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ReadUserUseCase readUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    
    public UserService(
        CreateUserUseCase createUserUseCase,
        DeleteUserUseCase deleteUserUseCase,
        ReadUserUseCase readUserUseCase,
        UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.readUserUseCase = readUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }
    
    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        return this.createUserUseCase.createUser(requestDTO);
    }
    @Override
    public DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO requestDTO) {
        return this.deleteUserUseCase.deleteUser(requestDTO);
    }
    @Override
    public ReadUserResponseDTO readUser(ReadUserRequestDTO requestDTO) {
        return this.readUserUseCase.readUser(requestDTO);
    }
    @Override
    public UpdateUserReponseDTO updateUser(UpdateUserRequestDTO requestDTO) {
        return this.updateUserUseCase.updateUser(requestDTO);
    }
}
