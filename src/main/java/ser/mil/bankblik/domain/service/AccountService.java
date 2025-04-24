package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.List;
import java.util.UUID;

@Component
public class AccountService {
    private final BlikRepository blikRepository;

    public AccountService(BlikRepository blikRepository) {
        this.blikRepository = blikRepository;
    }

    public List<Account> getAccounts() {
        return blikRepository.getAccounts();
    }

    public void saveAccount(String accountNumber, double balance) {
        blikRepository.save(new Account(UUID.randomUUID().toString(), accountNumber, balance));
    }
}
