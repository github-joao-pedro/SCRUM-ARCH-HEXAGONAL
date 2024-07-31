package api.scrum.project.domain.ports.in.users;

public interface AppendUserUseCase {
    UsersResponseDTO appendUser(UsersRequestDTO requestDTO);
}
