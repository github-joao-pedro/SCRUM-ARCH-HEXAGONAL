package api.scrum.sprint.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.in.read.ReadSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintUseCase;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;

public class ReadSprintUseCaseImpl implements ReadSprintUseCase {

    private final SprintRepositoryPort sprintRepositoryPort;
    private final ModelMapper modelMapper;
    
    public ReadSprintUseCaseImpl(SprintRepositoryPort sprintRepositoryPort, ModelMapper modelMapper) {
        this.sprintRepositoryPort = sprintRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReadSprintResponseDTO readSprint(ReadSprintRequestDTO requestDTO) {
        Sprint sprintExisting = this.sprintRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Sprint not found with 'id': "+requestDTO.getId(),"Provide a valid 'id'"));
        
        ReadSprintResponseDTO responseDTO = this.modelMapper.map(sprintExisting, ReadSprintResponseDTO.class);
        responseDTO.setProject(sprintExisting.getProject());
        return responseDTO;
    }
    
}
