package ser.mil.bankblik.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.*;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BlikRepositorySQL implements BlikRepository {
    private final UserRepositorySpringData userRepository;
    private final AccountRepositorySpringData accountRepository;

    private final BlikCodeRepositorySpringData blikCodeRepository;

    private final OrganizationRepositorySpringData organizationRepository;

    private final BlikTransactionRepositorySpringData blikTransactionRepository;

    public BlikRepositorySQL(UserRepositorySpringData userRepository, AccountRepositorySpringData accountRepository, BlikCodeRepositorySpringData blikCodeRepository, OrganizationRepositorySpringData organizationRepository, BlikTransactionRepositorySpringData blikTransactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.blikCodeRepository = blikCodeRepository;
        this.organizationRepository = organizationRepository;
        this.blikTransactionRepository = blikTransactionRepository;
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

    public Optional<Account> findByAccountNumber(String accountNumber) {
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

    public Optional<BlikCode> findByCode(int code) {
        return blikCodeRepository.findByCode(code);
    }

    public void deleteByCode(int code) {
        blikCodeRepository.deleteByCode(code);
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void save(BlikTransaction blikTransaction) {
        blikTransactionRepository.save(blikTransaction);
    }

    public Optional<Organization> findByName(String name) {
        return organizationRepository.findByName(name);
    }

    @Override
    public BlikTransaction getBlikTransactionById(String id) {
        return blikTransactionRepository.getBlikTransactionById(id);
    }

    @Transactional
    public void clearDatabase() {
        blikTransactionRepository.deleteAll();
        blikCodeRepository.deleteAll();
        userRepository.deleteAll();
        accountRepository.deleteAll();
        organizationRepository.deleteAll();
    }



}
