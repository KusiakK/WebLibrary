package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String LastName);
}
