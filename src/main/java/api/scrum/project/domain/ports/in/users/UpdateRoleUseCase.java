package api.scrum.project.domain.ports.in.users;

public interface UpdateRoleUseCase {
    UsersResponseDTO updateRole(UsersRequestDTO requestDTO);
}
