package api.scrum.authenticate.application.usecase;

import api.scrum.authenticate.domain.ports.in.validate.ValidateJwtDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateRequestDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateResponseDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateUseCase;
import api.scrum.authenticate.domain.ports.out.AuthJwtPort;

public class ValidateUseCaseImpl implements ValidateUseCase {

    private final AuthJwtPort jwtPort;
    
    public ValidateUseCaseImpl(AuthJwtPort jwtPort) {
        this.jwtPort = jwtPort;
    }

    @Override
    public ValidateResponseDTO validate(ValidateRequestDTO requestDTO) {
        ValidateJwtDTO jwtDTO = this.jwtPort.readToken(requestDTO.getToken());
        return new ValidateResponseDTO("success","Token is valid",jwtDTO);
    }
    
}
