package api.scrum.task.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import api.scrum.task.domain.model.Task;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;
import api.scrum.task.infrastructure.entities.TaskEntity;
import api.scrum.task.infrastructure.repositories.JpaTaskRepository;

public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;
    private final ModelMapper modelMapper;

    public JpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository, ModelMapper modelMapper) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = this.modelMapper.map(task, TaskEntity.class);
        TaskEntity savedTaskEntity = this.jpaTaskRepository.save(taskEntity);
        return this.modelMapper.map(savedTaskEntity, Task.class);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        Optional<TaskEntity> taskSaved = this.jpaTaskRepository.findById(id);
        if (taskSaved.isPresent()) {
            Task task = this.modelMapper.map(taskSaved.get(), Task.class);
            return Optional.of(task);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaTaskRepository.existsById(id);
    }

    @Override
    public void delete(Task task) {
        this.jpaTaskRepository.delete(this.modelMapper.map(task, TaskEntity.class));
    }
    

}
