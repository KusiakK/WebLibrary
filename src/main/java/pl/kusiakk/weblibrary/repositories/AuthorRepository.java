package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAll();
}
