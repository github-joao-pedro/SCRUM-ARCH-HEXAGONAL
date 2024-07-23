package api.scrum.user.domain.ports.in.create;

public interface CreateUserUseCase {
    CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO);
}
