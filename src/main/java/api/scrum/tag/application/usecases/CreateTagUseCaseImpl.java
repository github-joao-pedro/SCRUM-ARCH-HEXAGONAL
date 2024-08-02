package api.scrum.tag.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.in.create.CreateTagRequestDTO;
import api.scrum.tag.domain.ports.in.create.CreateTagResponseDTO;
import api.scrum.tag.domain.ports.in.create.CreateTagUseCase;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;

public class CreateTagUseCaseImpl implements CreateTagUseCase {

    private final TagRepositoryPort tagRepositoryPort;
    private final ModelMapper modelMapper;
    
    public CreateTagUseCaseImpl(TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        this.tagRepositoryPort = tagRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateTagResponseDTO createTag(CreateTagRequestDTO requestDTO) {
        Tag tag = this.modelMapper.map(requestDTO, Tag.class);
        Tag saved = this.tagRepositoryPort.save(tag);
        return this.modelMapper.map(saved, CreateTagResponseDTO.class);
    }
    
}
