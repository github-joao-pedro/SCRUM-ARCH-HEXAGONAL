package api.scrum.sprint.application.services;

import api.scrum.sprint.domain.ports.in.create.CreateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.create.CreateSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.create.CreateSprintUseCase;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.delete.DeleteSprintUseCase;
import api.scrum.sprint.domain.ports.in.read.ReadSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.read.ReadSprintUseCase;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintRequestDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintResponseDTO;
import api.scrum.sprint.domain.ports.in.update.UpdateSprintUseCase;

public class SprintService implements 
    CreateSprintUseCase,
    DeleteSprintUseCase,
    ReadSprintUseCase,
    UpdateSprintUseCase {

    private final CreateSprintUseCase createSprintUseCase;
    private final DeleteSprintUseCase deleteSprintUseCase;
    private final ReadSprintUseCase readSprintUseCase;
    private final UpdateSprintUseCase updateSprintUseCase;

    public SprintService(CreateSprintUseCase createSprintUseCase, DeleteSprintUseCase deleteSprintUseCase,
            ReadSprintUseCase readSprintUseCase, UpdateSprintUseCase updateSprintUseCase) {
        this.createSprintUseCase = createSprintUseCase;
        this.deleteSprintUseCase = deleteSprintUseCase;
        this.readSprintUseCase = readSprintUseCase;
        this.updateSprintUseCase = updateSprintUseCase;
    }

    @Override
    public CreateSprintResponseDTO createSprint(CreateSprintRequestDTO requestDTO) {
        return this.createSprintUseCase.createSprint(requestDTO);
    }

    @Override
    public DeleteSprintResponseDTO deleteSprint(DeleteSprintRequestDTO requestDTO) {
        return this.deleteSprintUseCase.deleteSprint(requestDTO);
    }
    @Override
    public ReadSprintResponseDTO readSprint(ReadSprintRequestDTO requestDTO) {
        return this.readSprintUseCase.readSprint(requestDTO);
    }
    @Override
    public UpdateSprintResponseDTO updateSprint(UpdateSprintRequestDTO requestDTO) {
        return this.updateSprintUseCase.updateSprint(requestDTO);
    }
}
