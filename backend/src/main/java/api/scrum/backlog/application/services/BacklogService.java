package api.scrum.backlog.application.services;

import api.scrum.backlog.domain.ports.in.read.ReadBacklogRequestDTO;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogResponseDTO;
import api.scrum.backlog.domain.ports.in.read.ReadBacklogUseCase;

public class BacklogService implements ReadBacklogUseCase {

    private final ReadBacklogUseCase readBacklogUseCase;

    public BacklogService(ReadBacklogUseCase readBacklogUseCase) {
        this.readBacklogUseCase = readBacklogUseCase;
    }

    @Override
    public ReadBacklogResponseDTO readBacklog(ReadBacklogRequestDTO requestDTO) {
        return this.readBacklogUseCase.readBacklog(requestDTO);
    }
    
}
