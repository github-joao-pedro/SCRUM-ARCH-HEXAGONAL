package api.scrum.status.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.in.update.UpdateStatusRequestDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusResponseDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusUseCase;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;

public class UpdateStatusUseCaseImpl implements UpdateStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;
    private final ModelMapper modelMapper;

    public UpdateStatusUseCaseImpl(StatusRepositoryPort statusRepositoryPort, ModelMapper modelMapper) {
        this.statusRepositoryPort = statusRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public UpdateStatusResponseDTO updateStatus(UpdateStatusRequestDTO requestDTO) {
        Status statusExisting = this.statusRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404,"Status not found", ""));
        
        if (requestDTO.getName() != null) {
            statusExisting.setName(requestDTO.getName());
        }
        Status saved = this.statusRepositoryPort.save(statusExisting);
        return this.modelMapper.map(saved, UpdateStatusResponseDTO.class);
    }
    
}
