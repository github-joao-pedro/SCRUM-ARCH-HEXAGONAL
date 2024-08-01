package api.scrum.status.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.in.delete.DeleteStatusRequestDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusResponseDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusUseCase;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;

public class DeleteStatusUseCaseImpl implements DeleteStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;
    private final ModelMapper modelMapper;

    public DeleteStatusUseCaseImpl(StatusRepositoryPort statusRepositoryPort, ModelMapper modelMapper) {
        this.statusRepositoryPort = statusRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteStatusResponseDTO deleteStatus(DeleteStatusRequestDTO requestDTO) {
        Status status = this.statusRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404,"Status not found", ""));
        this.statusRepositoryPort.delete(status);
        return this.modelMapper.map(status, DeleteStatusResponseDTO.class);
    }
    
}
