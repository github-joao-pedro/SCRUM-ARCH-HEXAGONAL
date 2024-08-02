package api.scrum.tag.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.in.delete.DeleteTagRequestDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagResponseDTO;
import api.scrum.tag.domain.ports.in.delete.DeleteTagUseCase;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;

public class DeleteTagUseCaseImpl implements DeleteTagUseCase {

    private final TagRepositoryPort tagRepositoryPort;
    private final ModelMapper modelMapper;
    
    public DeleteTagUseCaseImpl(TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        this.tagRepositoryPort = tagRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public DeleteTagResponseDTO deleteTag(DeleteTagRequestDTO requestDTO) {
        Tag tag = this.tagRepositoryPort.findById(requestDTO.getId())
            .orElseThrow(() -> new ApplicationException(404, "Tag not found", ""));
        this.tagRepositoryPort.delete(tag);
        return this.modelMapper.map(tag, DeleteTagResponseDTO.class);
    }
    
}
