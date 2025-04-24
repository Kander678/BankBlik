package ser.mil.bankblik.infrastructure.repository;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BlikRepositorySQL implements BlikRepository {
    private final UserRepositorySpringData userRepository;
    private final AccountRepositorySpringData accountRepository;

    public BlikRepositorySQL(UserRepositorySpringData userRepository, AccountRepositorySpringData accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<Account> findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

}
