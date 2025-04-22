package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.Account;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {
    private final BlikRepository blikRepository;
    private final AccountService accountService;

    public UserService(BlikRepository blikRepository, AccountService accountService) {
        this.blikRepository = blikRepository;
        this.accountService = accountService;
    }

    public void saveUser(String name, String email, String phone, Double balance) {
       blikRepository.save(new User(UUID.randomUUID().toString(), name, email, phone, balance));
    }
    public List<User> getUsers(){
        return blikRepository.getUsers();
    }

    public void pairAccountWithUser(String emailUser,String accountNumber){
        User user=findUserByEmail(emailUser);
        Account account=accountService.findByAccountNumber(accountNumber);
        user.setAccounts(account);
    }
    public User findUserByEmail(String email){
        System.out.println(blikRepository.getUsers());
        return blikRepository.getUsers().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(
                 () -> new RuntimeException("User not found")
        );

    }
}
