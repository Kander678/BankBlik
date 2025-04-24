package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;
import ser.mil.bankblik.infrastructure.repository.AccountRepositorySpringData;
import ser.mil.bankblik.infrastructure.repository.UserRepositorySpringData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {
    private final BlikRepository blikRepository;
    private final UserRepositorySpringData userRepository;
    private final AccountRepositorySpringData accountRepository;

    public UserService(BlikRepository blikRepository, UserRepositorySpringData userRepository, AccountRepositorySpringData accountRepository) {
        this.blikRepository = blikRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void saveUser(String name, String email, String phone, Double balance) {
       blikRepository.save(new User(UUID.randomUUID().toString(), name, email, phone, balance));
    }
    public List<User> getUsers(){
        return blikRepository.getUsers();
    }

    public void pairAccountWithUser(String emailUser,String accountNumber){
        User user=blikRepository.findUserByEmail(emailUser).orElseThrow();
        Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        user.setAccounts(account);
    }
}
