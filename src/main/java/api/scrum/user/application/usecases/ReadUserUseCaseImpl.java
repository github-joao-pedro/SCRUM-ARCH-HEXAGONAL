package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.read.ReadUserRequestDTO;
import api.scrum.user.domain.ports.in.read.ReadUserResponseDTO;
import api.scrum.user.domain.ports.in.read.ReadUserUseCase;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

public class ReadUserUseCaseImpl implements ReadUserUseCase {
    
    private final UserRepositoryPort userRepository;
    private final ModelMapper modelMapper;

    public ReadUserUseCaseImpl(UserRepositoryPort userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReadUserResponseDTO readUser(ReadUserRequestDTO requestDTO) {
        User user = new User();
        if (requestDTO.getId() != null) {
            user = this.userRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new ApplicationException(404,"User not found with 'id': "+requestDTO.getId(),"Provide a valid 'id'"));
        } else if (requestDTO.getNickname() != null) {
            user = this.userRepository.findByNickname(requestDTO.getNickname())
                .orElseThrow(() -> new ApplicationException(404,"User not found with 'nickname': "+requestDTO.getNickname(),"Provide a valid 'nickname'"));
        } else if (requestDTO.getEmail() != null) {
            user = this.userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new ApplicationException(404,"User not found with 'email': "+requestDTO.getEmail(),"Provide a valid 'email'"));
        }
        if (user.getId() == null) {
            throw new ApplicationException(400,"Invalid request","Provide a valid 'id', 'nickname' or 'email'");
        }
        return this.modelMapper.map(user, ReadUserResponseDTO.class);
    }
}
