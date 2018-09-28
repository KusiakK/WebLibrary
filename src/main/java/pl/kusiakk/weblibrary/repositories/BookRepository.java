package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
}
