package api.scrum.status.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.in.create.CreateStatusRequestDTO;
import api.scrum.status.domain.ports.in.create.CreateStatusResponseDTO;
import api.scrum.status.domain.ports.in.create.CreateStatusUseCase;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;

public class CreateStatusUseCaseImpl implements CreateStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;
    private final ModelMapper modelMapper;

    public CreateStatusUseCaseImpl(StatusRepositoryPort statusRepositoryPort, ModelMapper modelMapper) {
        this.statusRepositoryPort = statusRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateStatusResponseDTO createStatus(CreateStatusRequestDTO requestDTO) {
        Status status = this.modelMapper.map(requestDTO, Status.class);
        Status saved = this.statusRepositoryPort.save(status);
        return this.modelMapper.map(saved, CreateStatusResponseDTO.class);
    }
}
