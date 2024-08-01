package api.scrum.backlog.application.usecases;

import api.scrum.backlog.domain.model.Backlog;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogRequestDTO;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogResponseDTO;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogUseCase;
import api.scrum.backlog.domain.ports.out.BacklogRepositoryPort;
import api.scrum.exceptions.domain.ApplicationException;

public class ReadBacklogUseCaseImpl implements ReadBacklogUseCase {

    private final BacklogRepositoryPort backlogRepositoryPort;
    
    public ReadBacklogUseCaseImpl(BacklogRepositoryPort backlogRepositoryPort) {
        this.backlogRepositoryPort = backlogRepositoryPort;
    }

    @Override
    public ReadBacklogResponseDTO readBacklog(ReadBacklogRequestDTO requestDTO) {
        if (requestDTO.getBacklogId() != null) {
            Backlog backlog = this.backlogRepositoryPort.findById(requestDTO.getBacklogId())
                .orElseThrow(() -> new ApplicationException(404, "Backlog not found", ""));
            return new ReadBacklogResponseDTO(backlog.getId(), backlog.getProject());
        }
        if (requestDTO.getProjectId() != null) {
            Backlog backlog = this.backlogRepositoryPort.findByProjectId(requestDTO.getProjectId())
                .orElseThrow(() -> new ApplicationException(404, "Backlog not found", ""));
            return new ReadBacklogResponseDTO(backlog.getId(), backlog.getProject());
        }
        throw new ApplicationException(400,"Invalid request","Provide a valid 'backlogId' or 'projectId'");
    }
    
}
