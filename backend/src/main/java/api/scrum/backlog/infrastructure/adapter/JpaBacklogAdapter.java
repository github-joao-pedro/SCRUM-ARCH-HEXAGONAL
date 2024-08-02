package api.scrum.backlog.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import api.scrum.backlog.domain.model.Backlog;
import api.scrum.backlog.domain.ports.out.BacklogRepositoryPort;
import api.scrum.backlog.infrastructure.entities.BacklogEntity;
import api.scrum.backlog.infrastructure.repositories.JpaBacklogRepository;

public class JpaBacklogAdapter implements BacklogRepositoryPort {
    
    private final JpaBacklogRepository backlogRepository;
    private final ModelMapper modelMapper;

    public JpaBacklogAdapter(JpaBacklogRepository backlogRepository, ModelMapper modelMapper) {
        this.backlogRepository = backlogRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Backlog save(Backlog backlog) {
        BacklogEntity backlogEntity = this.modelMapper.map(backlog, BacklogEntity.class);
        BacklogEntity saved = this.backlogRepository.save(backlogEntity);
        return this.modelMapper.map(saved, Backlog.class);
    }
    @Override
    public Optional<Backlog> findById(UUID id) {
        Optional<BacklogEntity> savedBacklog = this.backlogRepository.findById(id);
        if (savedBacklog.isPresent()) {
            Backlog backlog = this.modelMapper.map(savedBacklog.get(), Backlog.class);
            return Optional.of(backlog);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<Backlog> findByProjectId(UUID projectId) {
        Optional<BacklogEntity> savedBacklog = this.backlogRepository.findByProjectId(projectId);
        if (savedBacklog.isPresent()) {
            Backlog backlog = this.modelMapper.map(savedBacklog.get(), Backlog.class);
            return Optional.of(backlog);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public boolean existsById(UUID id) {
        return this.backlogRepository.existsById(id);
    }
    @Override
    public void delete(Backlog backlog) {
        BacklogEntity backlogEntity = this.modelMapper.map(backlog, BacklogEntity.class);
        this.backlogRepository.delete(backlogEntity);
    }
}
