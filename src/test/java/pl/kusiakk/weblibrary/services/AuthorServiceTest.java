package pl.kusiakk.weblibrary.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIfSaveReturnsAuthorFromRepository() {
        Author author = new Author();
        Author authorToCompare = new Author();
        authorToCompare.setAuthorID(0);

        when(authorRepository.save(author)).thenReturn(authorToCompare);

        assertEquals(authorToCompare, authorService.save(author));
    }
}