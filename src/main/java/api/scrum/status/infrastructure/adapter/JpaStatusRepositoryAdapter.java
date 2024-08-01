package api.scrum.status.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import api.scrum.status.domain.model.Status;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;
import api.scrum.status.infrastructure.entities.StatusEntity;
import api.scrum.status.infrastructure.repositories.JpaStatusRepository;

public class JpaStatusRepositoryAdapter implements StatusRepositoryPort {
    
    private final JpaStatusRepository jpaStatusRepository;
    private final ModelMapper modelMapper;
    
    public JpaStatusRepositoryAdapter(JpaStatusRepository jpaStatusRepository, ModelMapper modelMapper) {
        this.jpaStatusRepository = jpaStatusRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Status save(Status status) {
        StatusEntity statusEntity = this.modelMapper.map(status, StatusEntity.class);
        StatusEntity statusSaved = this.jpaStatusRepository.save(statusEntity);
        return this.modelMapper.map(statusSaved, Status.class);
    }
    @Override
    public Optional<Status> findById(UUID id) {
        Optional<StatusEntity> statusSaved = this.jpaStatusRepository.findById(id);
        if (statusSaved.isPresent()) {
            Status status = this.modelMapper.map(statusSaved.get(), Status.class);
            return Optional.of(status);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<List<Status>> findByProjectId(UUID projectId) {
        Optional<List<StatusEntity>> statusSaved = this.jpaStatusRepository.findByProjectId(projectId);
        if (statusSaved.isPresent()) {
            List<Status> status = statusSaved.get().stream().map(value -> this.modelMapper.map(value, Status.class)).collect(Collectors.toList());
            return Optional.of(status);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public boolean existsById(UUID id) {
        return this.jpaStatusRepository.existsById(id);
    }
    @Override
    public void delete(Status status) {
        this.jpaStatusRepository.delete(this.modelMapper.map(status, StatusEntity.class));
    }
}
