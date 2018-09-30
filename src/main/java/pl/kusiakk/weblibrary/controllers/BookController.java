package pl.kusiakk.weblibrary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kusiakk.weblibrary.domain.exceptions.BookNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Book;
import pl.kusiakk.weblibrary.repositories.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<Book> list() {
        return repository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id) throws BookNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id.toString())
        );
    }

    @PutMapping("/{id}")
    public Book update(@RequestBody Book newBook, @PathVariable Integer id){
        return repository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setCategory(newBook.getCategory());
                    book.setIsbn(newBook.getIsbn());
                    book.setPages(newBook.getPages());
                    book.setReleaseDate(newBook.getReleaseDate());
                    book.setSummary(newBook.getSummary());
                    book.setTitle(newBook.getTitle());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setBookID(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
