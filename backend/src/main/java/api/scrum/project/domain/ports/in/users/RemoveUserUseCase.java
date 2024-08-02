package api.scrum.project.domain.ports.in.users;

public interface RemoveUserUseCase {
    UsersResponseDTO removeUser(UsersRequestDTO requestDTO);
}
