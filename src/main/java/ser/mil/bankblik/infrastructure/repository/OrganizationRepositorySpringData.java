package ser.mil.bankblik.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.bankblik.domain.model.Organization;

import java.util.Optional;

public interface OrganizationRepositorySpringData extends JpaRepository<Organization, String> {
    Optional<Organization> findByName(String name);
}
