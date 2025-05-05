package ser.mil.bankblik.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.bankblik.domain.model.BlikTransaction;

public interface BlikTransactionRepositorySpringData extends JpaRepository<BlikTransaction, String> {
    BlikTransaction getBlikTransactionById(String id);
}
