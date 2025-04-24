package ser.mil.bankblik.infrastructure.repository;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.BlikCode;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BlikRepositorySQL implements BlikRepository {
    private final UserRepositorySpringData userRepository;
    private final AccountRepositorySpringData accountRepository;

    private final BlikCodeRepositorySpringData blikCodeRepository;

    public BlikRepositorySQL(UserRepositorySpringData userRepository, AccountRepositorySpringData accountRepository, BlikCodeRepositorySpringData blikCodeRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.blikCodeRepository = blikCodeRepository;
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

    @Override
    public void save(BlikCode blikCode) {
        blikCodeRepository.save(blikCode);
    }

    @Override
    public List<BlikCode> getBlikCodes() {
        return (List<BlikCode>) blikCodeRepository.findAll();
    }

    public Optional<BlikCode> findByCode(int code){
        return blikCodeRepository.findByCode(code);
    }

    public void deleteByCode(int code){
        blikCodeRepository.deleteByCode(code);
    }

}
