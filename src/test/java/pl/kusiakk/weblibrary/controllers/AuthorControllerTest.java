package pl.kusiakk.weblibrary.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthorControllerTest {

    @Autowired
    AuthorController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testIfListReturnsListOfAuthorsFromDB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/authors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray());
    }

    @Test
    public void testIfAuthorFromListMatchAuthorInDB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/authors"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value("Tolkien"));
    }

    @Test
    public void testIfReturnsCorrectSingleAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/authors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Tolkien"));
    }
}