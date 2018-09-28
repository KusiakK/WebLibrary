package pl.kusiakk.weblibrary.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;
    private Author authorToCompare;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        author = new Author();
        authorToCompare = new Author();
    }

    @Test
    public void testIfSaveReturnsObjectFromRepository() {
        author.setAuthorID(9);
        authorToCompare.setAuthorID(0);

        when(authorRepository.save(author)).thenReturn(authorToCompare);

        assertEquals(authorToCompare, authorService.save(author));
    }

    @Test
    public void testIfDeleteByIdReturnsGivenObject() throws AuthorNotFoundException {
        author.setAuthorID(9);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(9)).thenReturn(authorOptional);
        doNothing().when(authorRepository).deleteById(9);

        assertEquals(author, authorService.deleteById(9));
    }

    @Test
    public void testIfDeleteByIdCallsMethodFromRepository() throws AuthorNotFoundException {
        author.setAuthorID(9);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(9)).thenReturn(authorOptional);
        doNothing().when(authorRepository).deleteById(9);

        authorService.deleteById(9);

        verify(authorRepository).deleteById(9);
    }

    @Test
    public void testIfFindByIdReturnsObjectFromRepository() throws AuthorNotFoundException {
        author.setAuthorID(9);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(9)).thenReturn(authorOptional);

        assertEquals(author, authorService.findById(9));
    }
}