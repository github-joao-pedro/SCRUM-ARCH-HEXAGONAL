package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.update.UpdateUserReponseDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserRequestDTO;
import api.scrum.user.domain.ports.in.update.UpdateUserUseCase;
import api.scrum.user.domain.ports.out.BCryptPasswordPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    
    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordPort bCryptPasswordPort;
    private final ModelMapper modelMapper;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepositoryPort, BCryptPasswordPort bCryptPasswordPort,
            ModelMapper modelMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.bCryptPasswordPort = bCryptPasswordPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public UpdateUserReponseDTO updateUser(UpdateUserRequestDTO requestDTO) {
        User existingUser = this.userRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "User not found", "The user you are trying to update does not exist"));
        
        if (requestDTO.getNickname() != null) {
            if (!this.userRepositoryPort.findByNickname(requestDTO.getNickname()).isPresent()) {
                existingUser.setNickname(requestDTO.getNickname());
            } else {
                throw new ApplicationException(409, "Invalid nickname", "User nicknames are unique, use another nickname");
            }
        }
        if (requestDTO.getEmail() != null) {
            if (!this.userRepositoryPort.findByEmail(requestDTO.getEmail()).isPresent()) {
                existingUser.setEmail(requestDTO.getEmail());
            } else {
                throw new ApplicationException(409, "Invalid email", "User email are unique, use another email");
            }
        }
        if (requestDTO.getPassword() != null) {
            existingUser.setPassword(this.bCryptPasswordPort.encode(requestDTO.getPassword()));
        }
        if (requestDTO.getName() != null) {
            existingUser.setName(requestDTO.getName());
        }
        if (requestDTO.getProfilePicture() != null) {
            existingUser.setProfilePicture(requestDTO.getProfilePicture());
        }
        return this.modelMapper.map(this.userRepositoryPort.save(existingUser), UpdateUserReponseDTO.class);
    }
}
