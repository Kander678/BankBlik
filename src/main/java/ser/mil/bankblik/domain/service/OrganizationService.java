package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.Organization;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.UUID;
@Component
public class OrganizationService {
    private final BlikRepository blikRepository;

    public OrganizationService(BlikRepository blikRepository) {
        this.blikRepository = blikRepository;
    }

    public void createOrganization(String organizationName,String accountName){
        Account account=blikRepository.findByAccountNumber(accountName).orElseThrow();
        Organization organization=new Organization(UUID.randomUUID().toString(),organizationName,account);
        blikRepository.save(organization);
    }

}
