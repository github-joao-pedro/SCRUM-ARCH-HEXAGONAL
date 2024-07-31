package api.scrum.sprint.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import api.scrum.sprint.domain.model.Sprint;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;
import api.scrum.sprint.infrastructure.entities.SprintEntity;
import api.scrum.sprint.infrastructure.repositories.JpaSprintRepository;

public class JpaSprintAdapter implements SprintRepositoryPort {

    private final JpaSprintRepository sprintRepository;
    private final ModelMapper modelMapper;
    
    public JpaSprintAdapter(JpaSprintRepository sprintRepository, ModelMapper modelMapper) {
        this.sprintRepository = sprintRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Sprint save(Sprint sprint) {
        SprintEntity sprintEntity = this.modelMapper.map(sprint, SprintEntity.class);
        SprintEntity saved = this.sprintRepository.save(sprintEntity);
        return this.modelMapper.map(saved, Sprint.class);
    }

    @Override
    public Optional<Sprint> findById(UUID id) {
        Optional<SprintEntity> savedSprint = this.sprintRepository.findById(id);

        if (savedSprint.isPresent()) {
            Sprint sprint = this.modelMapper.map(savedSprint.get(), Sprint.class);
            return Optional.of(sprint);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return this.sprintRepository.existsById(id);
    }

    @Override
    public void delete(Sprint sprint) {
        SprintEntity sprintEntity = this.modelMapper.map(sprint, SprintEntity.class);
        this.sprintRepository.delete(sprintEntity);
    }
    
}
