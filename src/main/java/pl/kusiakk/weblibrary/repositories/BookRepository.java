package pl.kusiakk.weblibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kusiakk.weblibrary.domain.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
