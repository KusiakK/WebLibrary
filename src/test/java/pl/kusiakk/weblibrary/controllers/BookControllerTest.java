package pl.kusiakk.weblibrary.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kusiakk.weblibrary.repositories.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        int tolkienId = 1;
        mockMvc.perform(get("/api/v1/books/{id}", tolkienId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(9780007488315L));
    }

}