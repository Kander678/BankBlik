package ser.mil.bankblik.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.bankblik.domain.model.User;

import java.util.Optional;

public interface UserRepositorySpringData extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
