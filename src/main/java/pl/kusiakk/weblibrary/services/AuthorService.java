package pl.kusiakk.weblibrary.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }

    public Author findById(int id) throws AuthorNotFoundException {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
