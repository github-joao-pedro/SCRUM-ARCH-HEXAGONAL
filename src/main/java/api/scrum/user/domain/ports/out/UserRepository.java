package api.scrum.user.domain.ports.out;

import java.util.Optional;
import java.util.UUID;

import api.scrum.user.domain.model.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    boolean existsById(UUID id);
    void delete(User user);
}
