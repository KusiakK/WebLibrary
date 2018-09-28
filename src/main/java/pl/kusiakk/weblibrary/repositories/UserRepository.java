package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kusiakk.weblibrary.domain.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
