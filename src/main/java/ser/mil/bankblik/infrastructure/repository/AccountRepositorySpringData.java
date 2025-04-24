package ser.mil.bankblik.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.bankblik.domain.model.Account;

import java.util.Optional;

public interface AccountRepositorySpringData extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
