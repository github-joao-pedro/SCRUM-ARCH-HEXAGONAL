package api.scrum.user.domain.ports.in.update;

public interface UpdateUserUseCase {
    UpdateUserReponseDTO updateUser(UpdateUserRequestDTO requestDTO);
}
