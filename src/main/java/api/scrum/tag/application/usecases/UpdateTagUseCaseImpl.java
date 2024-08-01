package api.scrum.tag.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.in.update.UpdateTagRequestDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagResponseDTO;
import api.scrum.tag.domain.ports.in.update.UpdateTagUseCase;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;

public class UpdateTagUseCaseImpl implements UpdateTagUseCase {

    private final TagRepositoryPort tagRepositoryPort;
    private final ModelMapper modelMapper;
    
    public UpdateTagUseCaseImpl(TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        this.tagRepositoryPort = tagRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public UpdateTagResponseDTO updateStatus(UpdateTagRequestDTO requestDTO) {
        Tag tag = this.tagRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Tag not found", ""));
        if (requestDTO.getName() != null) {
            tag.setName(requestDTO.getName());
        }
        Tag saved = this.tagRepositoryPort.save(tag);
        return this.modelMapper.map(saved, UpdateTagResponseDTO.class);
    }
    
}
