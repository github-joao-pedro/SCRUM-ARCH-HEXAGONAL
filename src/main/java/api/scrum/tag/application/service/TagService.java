package api.scrum.tag.application.service;

import api.scrum.tag.domain.ports.in.create.CreateTagRequestDTO;
import api.scrum.tag.domain.ports.in.create.CreateTagResponseDTO;
import api.scrum.tag.domain.ports.in.create.CreateTagUseCase;
import api.scrum.tag.domain.ports.in.delete.DeleteTagRequestDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagResponseDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagUseCase;
import api.scrum.tag.domain.ports.in.read.ReadTagRequestDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagResponseDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagUseCase;
import api.scrum.tag.domain.ports.in.update.UpdateTagRequestDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagResponseDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagUseCase;

public class TagService implements 
    CreateTagUseCase,
    DeleteTagUseCase,
    ReadTagUseCase,
    UpdateTagUseCase {
    
    private final CreateTagUseCase createTagUseCase;
    private final DeleteTagUseCase deleteTagUseCase;
    private final ReadTagUseCase readTagUseCase;
    private final UpdateTagUseCase updateTagUseCase;

    public TagService(
        CreateTagUseCase createTagUseCase,
        DeleteTagUseCase deleteTagUseCase,
        ReadTagUseCase readTagUseCase,
        UpdateTagUseCase updateTagUseCase) {
        this.createTagUseCase = createTagUseCase;
        this.deleteTagUseCase = deleteTagUseCase;
        this.readTagUseCase = readTagUseCase;
        this.updateTagUseCase = updateTagUseCase;
    }

    @Override
    public CreateTagResponseDTO createStatus(CreateTagRequestDTO requestDTO) {
        return this.createTagUseCase.createStatus(requestDTO);
    }

    @Override
    public DeleteTagResponseDTO deleteStatus(DeleteTagRequestDTO requestDTO) {
        return this.deleteTagUseCase.deleteStatus(requestDTO);
    }

    @Override
    public ReadTagResponseDTO readStatus(ReadTagRequestDTO requestDTO) {
        return this.readTagUseCase.readStatus(requestDTO);
    }

    @Override
    public UpdateTagResponseDTO updateStatus(UpdateTagRequestDTO requestDTO) {
        return this.updateTagUseCase.updateStatus(requestDTO);
    }
    
}
