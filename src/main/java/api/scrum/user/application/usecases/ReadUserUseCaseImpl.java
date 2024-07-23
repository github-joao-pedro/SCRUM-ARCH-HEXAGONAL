package api.scrum.user.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.in.read.ReadUserRequestDTO;
import api.scrum.user.domain.ports.in.read.ReadUserResponseDTO;
import api.scrum.user.domain.ports.in.read.ReadUserUseCase;
import api.scrum.user.domain.ports.out.UserRepository;

public class ReadUserUseCaseImpl implements ReadUserUseCase {
    
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ReadUserUseCaseImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReadUserResponseDTO readUser(ReadUserRequestDTO requestDTO) {
        // TODO: Regras de negócio
        User user = new User();
        if (requestDTO.getId() != null) {
            user = this.userRepository.findById(requestDTO.getId()).get();
        } else if (requestDTO.getNickname() != null) {
            user = this.userRepository.findByNickname(requestDTO.getNickname()).get();
        } else if (requestDTO.getEmail() != null) {
            user = this.userRepository.findByEmail(requestDTO.getEmail()).get();
        }
        return this.modelMapper.map(user, ReadUserResponseDTO.class);
    }
}
