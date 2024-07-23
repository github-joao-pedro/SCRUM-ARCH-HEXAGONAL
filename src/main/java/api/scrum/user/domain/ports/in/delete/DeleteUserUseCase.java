package api.scrum.user.domain.ports.in.delete;

public interface DeleteUserUseCase {
    DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO requestDTO);
}
