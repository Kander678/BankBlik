package ser.mil.bankblik.infrastructure.repository;

import org.springframework.stereotype.Component;
import ser.mil.bankblik.domain.model.User;
import ser.mil.bankblik.domain.repository.BlikRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlikRepositorySQL implements BlikRepository {
    private final UserRepositorySpringData userRepository;

    public BlikRepositorySQL(UserRepositorySpringData userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
