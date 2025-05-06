package ser.mil.bankblik.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import ser.mil.bankblik.domain.model.BlikCode;

import java.util.Optional;

public interface BlikCodeRepositorySpringData extends CrudRepository<BlikCode, String> {
    Optional<BlikCode> findByCode(int code);

    @Transactional
    void deleteByCode(int code);
}
