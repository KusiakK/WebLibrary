package pl.kusiakk.weblibrary.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.exceptions.BookNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.domain.models.Book;
import pl.kusiakk.weblibrary.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kusiakk.weblibrary.controllers.AuthorControllerTest.asJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {
    @Autowired
    BookController controller;

    @Autowired
    BookRepository repository;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testIfListReturnsListOfBooksFromDB() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray());
    }

    @Test
    public void testIfBookFromListMatchBookInDB() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].isbn").value(9780007488315L));
    }

    @Test
    public void testIfReturnsCorrectSingleBook() throws Exception {
        int fellowshipId = 1;
        mockMvc.perform(get("/api/v1/books/{id}", fellowshipId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(9780007488315L));
    }
    @Test
    public void testIfPutModifyExistingBook() throws Exception {
        Book book = new Book();
        book.setIsbn("9780007488319");
        Book testBook = new Book();
        int fellowshipId = 1;

        mockMvc.perform(put("/api/v1/books/{id}", fellowshipId)
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        testBook = repository.findById(fellowshipId).orElseThrow(BookNotFoundException::new);

        assertThat(testBook.getIsbn()).isEqualTo(book.getIsbn());
    }




}