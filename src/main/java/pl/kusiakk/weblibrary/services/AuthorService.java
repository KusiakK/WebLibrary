package pl.kusiakk.weblibrary.services;


import org.springframework.stereotype.Service;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author delete(Author author) throws AuthorNotFoundException {
        return deleteById(author.getAuthorID());
    }

    public Author deleteById(Integer id) throws AuthorNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);
        Author existingAuthor = authorOptional.orElseThrow(AuthorNotFoundException::new);
        authorRepository.deleteById(id);
        return existingAuthor;
    }

    public Author findById(int id) throws AuthorNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.orElseThrow(AuthorNotFoundException::new);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
