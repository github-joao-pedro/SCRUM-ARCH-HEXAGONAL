package api.scrum.user.domain.ports.in.read;

public interface ReadUserUseCase {
    ReadUserResponseDTO readUser(ReadUserRequestDTO requestDTO);
}
