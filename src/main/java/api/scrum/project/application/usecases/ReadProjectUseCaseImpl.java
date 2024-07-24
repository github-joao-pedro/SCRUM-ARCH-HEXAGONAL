package api.scrum.project.application.usecases;

import org.modelmapper.ModelMapper;

import api.scrum.exceptions.domain.ApplicationException;
import api.scrum.project.domain.model.Project;
import api.scrum.project.domain.ports.in.read.ReadProjectRequestDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectResponseDTO;
import api.scrum.project.domain.ports.in.read.ReadProjectUseCase;
import api.scrum.project.domain.ports.out.ProjectRepositoryPort;

public class ReadProjectUseCaseImpl implements ReadProjectUseCase {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final ModelMapper modelMapper;
    
    public ReadProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, ModelMapper modelMapper) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReadProjectResponseDTO readProject(ReadProjectRequestDTO requestDTO) {
        if (requestDTO.getId() != null) {
            Project project = this.projectRepositoryPort.findById(requestDTO.getId())
                .orElseThrow(() -> new ApplicationException(404, "Project not found with 'id': "+requestDTO.getId(), "Provide a valid 'id'"));
            return this.modelMapper.map(project, ReadProjectResponseDTO.class);
        } else {
            throw new ApplicationException(400,"Invalid request","Provide a valid 'id'");
        }
    }
    
}
