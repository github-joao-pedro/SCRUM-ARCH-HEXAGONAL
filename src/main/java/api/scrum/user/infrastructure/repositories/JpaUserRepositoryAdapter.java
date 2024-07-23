package api.scrum.user.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import api.scrum.user.domain.model.User;
import api.scrum.user.domain.ports.out.UserRepository;
import api.scrum.user.infrastructure.entities.UserEntity;

public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final ModelMapper modelMapper;
    
    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository, ModelMapper modelMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = this.modelMapper.map(user, UserEntity.class);
        UserEntity userSaved = this.jpaUserRepository.save(userEntity);
        return this.modelMapper.map(userSaved, User.class);
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<UserEntity> userSaved = this.jpaUserRepository.findById(id);
        if (userSaved.isPresent()) {
            User user = this.modelMapper.map(userSaved.get(), User.class);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userSaved = this.jpaUserRepository.findByEmail(email);
        if (userSaved.isPresent()) {
            User user = this.modelMapper.map(userSaved.get(), User.class);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        Optional<UserEntity> userSaved = this.jpaUserRepository.findByNickname(nickname);
        if (userSaved.isPresent()) {
            User user = this.modelMapper.map(userSaved.get(), User.class);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaUserRepository.existsById(id);
    }

    @Override
    public void delete(User user) {
        this.jpaUserRepository.delete(this.modelMapper.map(user, UserEntity.class));
    }
    
}
