package pl.kusiakk.weblibrary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kusiakk.weblibrary.domain.DTOs.BookDTO;
import pl.kusiakk.weblibrary.domain.exceptions.BookNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Book;
import pl.kusiakk.weblibrary.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<BookDTO> list() {
        return repository
                .findAll()
                .stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Integer id) throws BookNotFoundException {
        return repository
                .findById(id)
                .map(BookDTO::new)
                .orElseThrow(
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
