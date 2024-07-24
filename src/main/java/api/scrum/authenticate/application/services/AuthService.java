package api.scrum.authenticate.application.services;

import api.scrum.authenticate.domain.ports.in.auth.AuthRequestDTO;
import api.scrum.authenticate.domain.ports.in.auth.AuthResponseDTO;
import api.scrum.authenticate.domain.ports.in.auth.AuthUseCase;
import api.scrum.authenticate.domain.ports.in.validate.ValidateRequestDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateResponseDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateUseCase;

public class AuthService implements AuthUseCase, ValidateUseCase {

    private final AuthUseCase authUseCase;
    private final ValidateUseCase validateUseCase;
    
    public AuthService(AuthUseCase authUseCase, ValidateUseCase validateUseCase) {
        this.authUseCase = authUseCase;
        this.validateUseCase = validateUseCase;
    }

    @Override
    public ValidateResponseDTO validate(ValidateRequestDTO requestDTO) {
        return this.validateUseCase.validate(requestDTO);
    }

    @Override
    public AuthResponseDTO auth(AuthRequestDTO requestDTO) {
        return this.authUseCase.auth(requestDTO);
    }
    
}
