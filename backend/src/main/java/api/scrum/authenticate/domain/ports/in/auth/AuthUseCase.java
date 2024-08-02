package api.scrum.authenticate.domain.ports.in.auth;

public interface AuthUseCase {
    AuthResponseDTO auth(AuthRequestDTO requestDTO);
}
