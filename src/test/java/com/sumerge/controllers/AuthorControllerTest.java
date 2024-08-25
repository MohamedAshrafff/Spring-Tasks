package com.sumerge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.exceptions.NotFoundException;

import com.sumerge.services.AuthorService;
import com.sumerge.task3.DTOs.AuthorDTO;
import com.sumerge.task3.DatabaseClasses.Author;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes =AuthorController.class)
@ComponentScan("com.sumerge.exceptions")
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author();
        author.setAuthor_birthdate("19-5-1999");
        author.setAuthor_name("John Not");
        author.setAuthor_email("not@test.com");

    }

    @Test
    public void addAuthorSuccessful() throws Exception {
        when(authorService.addAuthor(author)).thenReturn(new AuthorDTO());

        String authorJson = objectMapper.writeValueAsString(author);

         mockMvc.perform(post("/api/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    void getByIdSuccessful() throws Exception {
        when(authorService.getAuthorById(1)).thenReturn(author);
        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk());
    }


    @Test
    void getByIdNotSuccessful() throws Exception {
        when(authorService.getAuthorByIdDto(1)).thenThrow( new NotFoundException("Author with id 1 not found"));
        mockMvc.perform(get("/api/authors/1"))
                        .andExpect(status().isNotFound())
                        .andExpect(content().string("Author with id 1 not found"));
    }

    @Test
    void getByEmailSuccessful() throws Exception {
        when(authorService.getAuthorByEmail(author.getAuthor_email())).thenReturn(new AuthorDTO());
        mockMvc.perform(get("/api/authors/email")
                        .content(author.getAuthor_email())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByEmailNotSuccessful() throws Exception {
        when(authorService.getAuthorByEmail(author.getAuthor_email()))
                .thenThrow(new NotFoundException("Author with email not@test.com not found"));

        mockMvc.perform(get("/api/authors/email")
                        .content(author.getAuthor_email())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Author with email not@test.com not found"));
    }

}