package api.scrum.tag.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.in.read.ReadTagRequestDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagResponseDTO;
import api.scrum.tag.domain.ports.in.read.ReadTagUseCase;
import api.scrum.tag.domain.ports.in.read.TagDTO;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;

public class ReadTagUseCaseImpl implements ReadTagUseCase {

    private final TagRepositoryPort tagRepositoryPort;
    private final ModelMapper modelMapper;
    
    public ReadTagUseCaseImpl(TagRepositoryPort tagRepositoryPort, ModelMapper modelMapper) {
        this.tagRepositoryPort = tagRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReadTagResponseDTO readTag(ReadTagRequestDTO requestDTO) {
        if (requestDTO.getId() != null) {
            Tag tag = this.tagRepositoryPort.findById(requestDTO.getId())
                .orElseThrow(() -> new ApplicationException(404, "Tag not found", ""));
            TagDTO tagDTO = this.modelMapper.map(tag, TagDTO.class);
            return new ReadTagResponseDTO(List.of(tagDTO),tag.getProject());
        }
        if (requestDTO.getProjectId() != null) {
            List<Tag> tags = this.tagRepositoryPort.findByProjectId(requestDTO.getProjectId())
                .orElseThrow(() -> new ApplicationException(404, "Tag not found", ""));
            List<TagDTO> tagDTOs = tags.stream().map(value -> this.modelMapper.map(value, TagDTO.class)).collect(Collectors.toList());
            return new ReadTagResponseDTO(tagDTOs, tags.get(0).getProject());
        }
        throw new ApplicationException(400,"Invalid request","Provide a valid 'id' or 'projectId'");
        
    }
    
}
