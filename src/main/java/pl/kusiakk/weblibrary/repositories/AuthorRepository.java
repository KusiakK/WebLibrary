package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kusiakk.weblibrary.domain.models.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    public List<Author> findAll();
}
