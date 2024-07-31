package api.scrum.sprint.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintUseCase;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;

public class UpdateSprintUseCaseImpl implements UpdateSprintUseCase {

    private final SprintRepositoryPort sprintRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateSprintUseCaseImpl(SprintRepositoryPort sprintRepositoryPort, ModelMapper modelMapper) {
        this.sprintRepositoryPort = sprintRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public UpdateSprintResponseDTO updateSprint(UpdateSprintRequestDTO requestDTO) {
        Sprint sprintExisting = this.sprintRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Sprint not found with 'id': "+requestDTO.getId(),"Provide a valid 'id'"));
        
        if (requestDTO.getEndDate() != null) {
            sprintExisting.setEndDate(requestDTO.getEndDate());
        }
        if (requestDTO.getName() != null) {
            sprintExisting.setName(requestDTO.getName());
        }
        if (requestDTO.getStartDate() != null) {
            sprintExisting.setStartDate(requestDTO.getStartDate());
        }
        if (requestDTO.getStatus() != null) {
            sprintExisting.setStatus(requestDTO.getStatus());
        }

        Sprint newSprint = this.sprintRepositoryPort.save(sprintExisting);
        return this.modelMapper.map(newSprint, UpdateSprintResponseDTO.class);
    }
    
}
