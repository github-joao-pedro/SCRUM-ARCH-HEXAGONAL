package api.scrum.status.application.service;

import api.scrum.status.domain.ports.in.create.CreateStatusRequestDTO;
import api.scrum.status.domain.ports.in.create.CreateStatusResponseDTO;
import api.scrum.status.domain.ports.in.create.CreateStatusUseCase;
import api.scrum.status.domain.ports.in.delete.DeleteStatusRequestDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusResponseDTO;
import api.scrum.status.domain.ports.in.delete.DeleteStatusUseCase;
import api.scrum.status.domain.ports.in.read.ReadStatusRequestDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusResponseDTO;
import api.scrum.status.domain.ports.in.read.ReadStatusUseCase;
import api.scrum.status.domain.ports.in.update.UpdateStatusRequestDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusResponseDTO;
import api.scrum.status.domain.ports.in.update.UpdateStatusUseCase;

public class StatusService implements
    CreateStatusUseCase,
    DeleteStatusUseCase,
    ReadStatusUseCase,
    UpdateStatusUseCase {

    private CreateStatusUseCase createStatusUseCase;
    private DeleteStatusUseCase deleteStatusUseCase;
    private ReadStatusUseCase readStatusUseCase;
    private UpdateStatusUseCase updateStatusUseCase;

    
    public StatusService(CreateStatusUseCase createStatusUseCase, DeleteStatusUseCase deleteStatusUseCase,
            ReadStatusUseCase readStatusUseCase, UpdateStatusUseCase updateStatusUseCase) {
        this.createStatusUseCase = createStatusUseCase;
        this.deleteStatusUseCase = deleteStatusUseCase;
        this.readStatusUseCase = readStatusUseCase;
        this.updateStatusUseCase = updateStatusUseCase;
    }

    @Override
    public CreateStatusResponseDTO createStatus(CreateStatusRequestDTO requestDTO) {
        return this.createStatusUseCase.createStatus(requestDTO);
    }

    @Override
    public DeleteStatusResponseDTO deleteStatus(DeleteStatusRequestDTO requestDTO) {
        return this.deleteStatusUseCase.deleteStatus(requestDTO);
    }

    @Override
    public ReadStatusResponseDTO readStatus(ReadStatusRequestDTO requestDTO) {
        return this.readStatusUseCase.readStatus(requestDTO);
    }

    @Override
    public UpdateStatusResponseDTO updateStatus(UpdateStatusRequestDTO requestDTO) {
        return this.updateStatusUseCase.updateStatus(requestDTO);
    }
    
    
}
