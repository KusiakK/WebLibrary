package pl.kusiakk.weblibrary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kusiakk.weblibrary.domain.exceptions.AuthorNotFoundException;
import pl.kusiakk.weblibrary.domain.models.Author;
import pl.kusiakk.weblibrary.repositories.AuthorRepository;

import java.lang.reflect.Executable;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        int tolkienId = 1;
        mockMvc.perform(get("/api/v1/authors/{id}", tolkienId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Tolkien"));
    }

    @Test
    public void testIfPostCreatesNewAuthor() throws Exception {
        Author author = new Author();
        author.setFirstName("test");
        author.setLastName("test");
        int listSize = repository.findAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authors")
                .content(asJsonString(author))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertThat(repository.findAll().size()).isEqualTo(listSize + 1);
    }

    @Test
    public void testIfPutModifyExistingAuthor() throws Exception {
        Author author = new Author();
        Author testAuthor = null;
        author.setFirstName("Orson");
        author.setLastName("Scott");
        int tolkienId = 1;

        mockMvc.perform(put("/api/v1/authors/{id}", tolkienId)
                .content(asJsonString(author))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        testAuthor = repository.findById(tolkienId).orElseThrow(AuthorNotFoundException::new);

        assertThat(testAuthor.getLastName()).isEqualTo(author.getLastName());
    }

    @Test
    public void testIfDeleteDeletes() throws Exception {
        String pathToGibson = "/api/v1/authors/2";

        mockMvc.perform(get(pathToGibson))
                .andExpect(status().isOk());

        mockMvc.perform(delete(pathToGibson))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(pathToGibson));
//        TODO add assertion
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