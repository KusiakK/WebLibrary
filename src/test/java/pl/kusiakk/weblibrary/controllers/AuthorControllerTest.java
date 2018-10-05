package pl.kusiakk.weblibrary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthorControllerTest {

    @Autowired
    AuthorController controller;

    @Autowired
    AuthorRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testIfListReturnsListOfAuthorsFromDB() throws Exception {
        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray());
    }

    @Test
    public void testIfAuthorFromListMatchAuthorInDB() throws Exception {
        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value("Tolkien"));
    }

    @Test
    public void testIfReturnsCorrectSingleAuthor() throws Exception {
        mockMvc.perform(get("/api/v1/authors/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Tolkien"));
    }

    @Test
    public void testIfPostingCreatesNewAuthor() throws Exception {
        Author author = new Author();
        author.setFirstName("test");
        author.setLastName("test");
        int listSize = repository.findAll().size();

        mockMvc.perform(post("/api/v1/authors")
                .content(asJsonString(author))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertThat(repository.findAll().size()).isEqualTo(listSize + 1);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}