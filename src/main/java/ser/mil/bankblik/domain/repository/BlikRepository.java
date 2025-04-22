package ser.mil.bankblik.domain.repository;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.User;

import java.util.List;

public interface BlikRepository {
    void save(User user);
    List<User> getUsers();

    void save(Account account);
    List<Account> getAccounts();
}
