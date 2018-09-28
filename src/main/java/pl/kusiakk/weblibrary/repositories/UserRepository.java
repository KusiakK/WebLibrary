package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
}
