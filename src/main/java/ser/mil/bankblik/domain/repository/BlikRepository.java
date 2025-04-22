package ser.mil.bankblik.domain.repository;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.User;

import java.util.List;
@Component
public interface BlikRepository {
    void save(User user);
    List<User> getUsers();
}
