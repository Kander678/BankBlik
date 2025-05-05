package ser.mil.bankblik.domain.repository;

import ser.mil.bankblik.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface BlikRepository {
    void save(User user);

    List<User> getUsers();

    void save(Account account);

    List<Account> getAccounts();

    Optional<User> findUserByEmail(String email);

    Optional<Account> findByAccountNumber(String accountNumber);

    void save(BlikCode blikCode);

    List<BlikCode> getBlikCodes();

    Optional<BlikCode> findByCode(int code);

    void deleteByCode(int code);

    void save(Organization organization);
    void save(BlikTransaction blikTransaction);

    Optional<Organization> findByName(String name);

    BlikTransaction getBlikTransactionById(String id);
}
