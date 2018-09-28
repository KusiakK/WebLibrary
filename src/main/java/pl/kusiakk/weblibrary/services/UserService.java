package pl.kusiakk.weblibrary.services;

import pl.kusiakk.weblibrary.domain.models.User;

public class UserService {
    private UserService instance;
    private UserRepository userRepository;

    private UserService() {
        userRepository = new UserRepository();
    }

    public UserService getInstance() {
        if (null == instance) {
            instance = new UserService();
        }
        return instance;
    }

    public Boolean register(User user) {
        return null != userRepository.create(user);
    }
}
