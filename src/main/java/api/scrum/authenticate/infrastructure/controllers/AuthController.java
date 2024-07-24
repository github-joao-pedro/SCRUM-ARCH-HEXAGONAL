package api.scrum.authenticate.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.authenticate.application.services.AuthService;
import api.scrum.authenticate.domain.ports.in.auth.AuthRequestDTO;
import api.scrum.authenticate.domain.ports.in.auth.AuthResponseDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateRequestDTO;
import api.scrum.authenticate.domain.ports.in.validate.ValidateResponseDTO;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public AuthResponseDTO auth(@RequestBody AuthRequestDTO requestDTO) {
        return this.authService.auth(requestDTO);
    }

    @GetMapping
    public ValidateResponseDTO validate(
        @RequestBody(required = false) ValidateRequestDTO requestDTO,
        @RequestParam(required = false, name = "access_token") String accessToken) {
        return this.authService.validate(accessToken == null? requestDTO : new ValidateRequestDTO(accessToken));
    }
}
