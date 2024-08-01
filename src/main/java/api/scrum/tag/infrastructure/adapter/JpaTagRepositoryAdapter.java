package api.scrum.tag.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.scrum.tag.domain.model.Tag;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;
import api.scrum.tag.infrastructure.entities.TagEntity;
import api.scrum.tag.infrastructure.repositories.JpaTagRepository;

public class JpaTagRepositoryAdapter implements TagRepositoryPort {

    private final JpaTagRepository jpaTagRepository;
    private final ModelMapper modelMapper;
    
    public JpaTagRepositoryAdapter(JpaTagRepository jpaTagRepository, ModelMapper modelMapper) {
        this.jpaTagRepository = jpaTagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity tagEntity = modelMapper.map(tag, TagEntity.class);
        TagEntity savedTagEntity = jpaTagRepository.save(tagEntity);
        return this.modelMapper.map(savedTagEntity, Tag.class);
    }

    @Override
    public Optional<Tag> findById(UUID id) {
        Optional<TagEntity> tagSaved = this.jpaTagRepository.findById(id);
        if (tagSaved.isPresent()) {
            Tag tag = this.modelMapper.map(tagSaved.get(), Tag.class);
            return Optional.of(tag);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Tag>> findByProjectId(UUID projectId) {
        Optional<List<TagEntity>> tagSaved = this.jpaTagRepository.findByProjectId(projectId);
        if (tagSaved.isPresent()) {
            List<Tag> tag = tagSaved.get().stream().map(value -> this.modelMapper.map(value, Tag.class)).collect(Collectors.toList());
            return Optional.of(tag);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaTagRepository.existsById(id);
    }

    @Override
    public void delete(Tag tag) {
        this.jpaTagRepository.delete(this.modelMapper.map(tag, TagEntity.class));
    }
    
}
