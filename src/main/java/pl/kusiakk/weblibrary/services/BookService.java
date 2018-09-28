package pl.kusiakk.weblibrary.services;


import org.springframework.stereotype.Service;
import pl.kusiakk.weblibrary.domain.exceptions.BookNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Book;
import pl.kusiakk.weblibrary.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book findById(int id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
