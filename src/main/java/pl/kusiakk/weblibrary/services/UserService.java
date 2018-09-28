package pl.kusiakk.weblibrary.services;

import org.springframework.stereotype.Service;
import pl.kusiakk.weblibrary.domain.models.User;
import pl.kusiakk.weblibrary.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}
