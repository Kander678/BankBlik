package ser.mil.bankblik.domain.service;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.RoleStatus;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;

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
    public void getUsers(){
        blikRepository.getUsers();
    }
}
