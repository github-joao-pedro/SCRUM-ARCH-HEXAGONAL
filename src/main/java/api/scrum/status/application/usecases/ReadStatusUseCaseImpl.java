package api.scrum.status.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.in.read.ReadStatusRequestDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusResponseDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusUseCase;
import api.scrum.status.domain.ports.in.read.StatusDTO;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;

public class ReadStatusUseCaseImpl implements ReadStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;
    private final ModelMapper modelMapper;

    public ReadStatusUseCaseImpl(StatusRepositoryPort statusRepositoryPort, ModelMapper modelMapper) {
        this.statusRepositoryPort = statusRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReadStatusResponseDTO readStatus(ReadStatusRequestDTO requestDTO) {
        if (requestDTO.getId() != null) {
            Status status = this.statusRepositoryPort.findById(requestDTO.getId())
                .orElseThrow(() -> new ApplicationException(404,"Status not found", ""));
            
            StatusDTO statusDTO = this.modelMapper.map(status, StatusDTO.class);
            return new ReadStatusResponseDTO(List.of(statusDTO), status.getProject());
        }
        if (requestDTO.getProjectId() != null) {
            List<Status> status = this.statusRepositoryPort.findByProjectId(requestDTO.getProjectId())
                .orElseThrow(() -> new ApplicationException(404,"Status not found", ""));
            List<StatusDTO> statusDTO = status.stream().map(value -> this.modelMapper.map(value, StatusDTO.class)).collect(Collectors.toList());
            return new ReadStatusResponseDTO(statusDTO,status.get(0).getProject());
        }
        throw new ApplicationException(400,"Invalid request","Provide a valid 'id' or 'projectId'");
    }
    
}
