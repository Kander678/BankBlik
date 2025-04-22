package ser.mil.bankblik.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.bankblik.domain.model.User;

public interface UserRepositorySpringData extends JpaRepository<User, Long> {
}
