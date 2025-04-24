package ser.mil.bankblik.domain.repository;

import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.BlikCode;
import ser.mil.bankblik.domain.model.User;

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

}
