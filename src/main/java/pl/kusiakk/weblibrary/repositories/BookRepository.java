package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kusiakk.weblibrary.domain.models.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();
}
