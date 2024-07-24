package api.scrum.authenticate.domain.ports.in.validate;

public interface ValidateUseCase {
    ValidateResponseDTO validate(ValidateRequestDTO requestDTO);
}
