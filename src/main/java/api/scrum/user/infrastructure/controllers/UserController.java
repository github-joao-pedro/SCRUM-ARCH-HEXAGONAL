package api.scrum.user.infrastructure.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.scrum.user.application.services.UserService;
import api.scrum.user.domain.ports.in.create.CreateUserRequestDTO;
import api.scrum.user.domain.ports.in.create.CreateUserResponseDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserRequestDTO;
import api.scrum.user.domain.ports.in.delete.DeleteUserResponseDTO;
import api.scrum.user.domain.ports.in.read.ReadUserRequestDTO;
import api.scrum.user.domain.ports.in.read.ReadUserResponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserReponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserRequestDTO;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CreateUserResponseDTO create(@RequestBody CreateUserRequestDTO requestDTO) {
        return this.userService.createUser(requestDTO);
    }
    @DeleteMapping
    public DeleteUserResponseDTO delete(@RequestParam(name = "id") UUID id) {
        DeleteUserRequestDTO requestDTO = DeleteUserRequestDTO.builder()
            .id(id)
            .build();
        return this.userService.deleteUser(requestDTO);
    }
    @GetMapping
    public ReadUserResponseDTO read(
        @RequestParam(name = "id", required = false) UUID id,
        @RequestParam(name = "nickname", required = false) String nickname,
        @RequestParam(name = "email", required = false) String email 
    ) {
        ReadUserRequestDTO requestDTO = ReadUserRequestDTO.builder()
            .id(id)
            .email(email)
            .nickname(nickname)
            .build();
        return this.userService.readUser(requestDTO);
    }
    @PutMapping
    public UpdateUserReponseDTO update(@RequestBody UpdateUserRequestDTO requestDTO) {
        return this.userService.updateUser(requestDTO);
    }
}
