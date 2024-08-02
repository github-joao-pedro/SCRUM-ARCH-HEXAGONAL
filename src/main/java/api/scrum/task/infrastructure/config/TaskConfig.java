package api.scrum.task.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.scrum.backlog.domain.ports.out.BacklogRepositoryPort;
import api.scrum.sprint.domain.ports.out.SprintRepositoryPort;
import api.scrum.status.domain.ports.out.StatusRepositoryPort;
import api.scrum.tag.domain.ports.out.TagRepositoryPort;
import api.scrum.task.application.service.TaskService;
import api.scrum.task.application.usecases.CreateTaskUseCaseImpl;
import api.scrum.task.application.usecases.DeleteTaskUseCaseImpl;
import api.scrum.task.application.usecases.ReadTaskUseCaseImpl;
import api.scrum.task.application.usecases.UpdateTaskUseCaseImpl;
import api.scrum.task.domain.ports.out.TaskRepositoryPort;
import api.scrum.task.infrastructure.adapter.JpaTaskRepositoryAdapter;
import api.scrum.task.infrastructure.repositories.JpaTaskRepository;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

@Configuration
public class TaskConfig {
    
    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepository jpaTaskRepository, ModelMapper modelMapper) {
        return new JpaTaskRepositoryAdapter(jpaTaskRepository, modelMapper);
    }

    @Bean
    public TaskService taskService(
        TaskRepositoryPort taskRepositoryPort,
        BacklogRepositoryPort backlogRepositoryPort,
        SprintRepositoryPort sprintRepositoryPort,
        UserRepositoryPort userRepositoryPort,
        StatusRepositoryPort statusRepositoryPort,
        TagRepositoryPort tagRepositoryPort,
        ModelMapper modelMapper) {
        return new TaskService(
            new CreateTaskUseCaseImpl(taskRepositoryPort, backlogRepositoryPort, sprintRepositoryPort, userRepositoryPort, statusRepositoryPort, tagRepositoryPort, modelMapper),
            new DeleteTaskUseCaseImpl(taskRepositoryPort, modelMapper),
            new ReadTaskUseCaseImpl(taskRepositoryPort, modelMapper),
            new UpdateTaskUseCaseImpl(taskRepositoryPort, modelMapper)
        );
    }
}
