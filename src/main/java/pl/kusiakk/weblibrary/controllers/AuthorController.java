package pl.kusiakk.weblibrary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<Author> list() {
        return repository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody Author author) {
        return repository.save(author);
    }

    @GetMapping("/{id}")
    public Author get(@PathVariable Integer id) throws AuthorNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id.toString())
        );
    }

    @PutMapping("/{id}")
    public Author update(@RequestBody Author newAuthor, @PathVariable Integer id){
        return repository.findById(id)
                .map(author -> {
                    author.setFirstName(newAuthor.getFirstName());
                    author.setLastName(newAuthor.getLastName());
                    author.setBirthPlace(newAuthor.getBirthPlace());
                    return repository.save(author);
                })
                .orElseGet(() -> {
                    newAuthor.setAuthorID(id);
                    return repository.save(newAuthor);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
