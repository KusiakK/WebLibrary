package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
