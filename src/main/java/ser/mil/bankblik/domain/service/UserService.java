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

    public UserService(BlikRepository blikRepository) {
        this.blikRepository = blikRepository;
    }

    public void saveUser(String name, String email, String phone, Double balance) {
       blikRepository.save(new User(UUID.randomUUID().toString(), name, email, phone, balance));
    }
    public List<User> getUsers(){
        return blikRepository.getUsers();
    }

    public void pairAccountWithUser(String emailUser,String accountNumber){
        User user=blikRepository.findUserByEmail(emailUser).get();
        System.out.println("****************"+user);
        Account account=blikRepository.findByAccountNumber(accountNumber).get();
        System.out.println("****************"+account);
        user.setAccounts(account);
    }
    public Optional<User> findUserByEmail(String email){
        return blikRepository.findUserByEmail(email);
    }
}
