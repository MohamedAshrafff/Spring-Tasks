package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.AuthorMapper;
import com.sumerge.repos.JPAAuthorRepository;
import com.sumerge.task3.DTOs.AuthorDTO;
import com.sumerge.task3.DatabaseClasses.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @Mock
    private JPAAuthorRepository jpaAuthorRepository;

    @Mock
    AuthorMapper authorMapper;
    @InjectMocks
    private AuthorService authorService;

    @Test
    void getAuthorByEmailNotFound() {
        when(jpaAuthorRepository.findAuthorByEmail("test@test.com")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,() -> authorService.getAuthorByEmail("test@test.com"));

    }

    @Test
    void getAuthorByEmailFound() {
        Author author = new Author("ahmed","test@test.com","19-5-1998");
        when(jpaAuthorRepository.findAuthorByEmail("test@test.com")).thenReturn(Optional.of(author));
        AuthorDTO foundAuthor = authorService.getAuthorByEmail("test@test.com");
        assertEquals(authorMapper.authorToAuthorDTO(author) ,foundAuthor);
    }

    @Test
    void getAuthorByIdNotFound(){
        when(jpaAuthorRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,() -> authorService.getAuthorById(1));
    }

    @Test
    void getAuthorByIdFound(){
        Author author = new Author("ahmed","test@test.com","19-5-1998");
        when(jpaAuthorRepository.findById(1)).thenReturn(Optional.of(author));
        Author foundAuthor = authorService.getAuthorById(1);
        assertEquals(author ,foundAuthor);
    }

    @Test
    void addAuthor_Successful(){
        Author author = new Author("ahmed","test@test.com","19-5-1998");
        when(jpaAuthorRepository.save(author)).thenReturn(author);
        assertDoesNotThrow(() -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(1)).save(author);
    }

    @Test
    void addAuthor_NotSuccessful_NullAuthor(){
        Author author = null;
        assertThrows(IllegalArgumentException.class , () -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(0)).save(author);
    }

    @Test
    void addAuthor_NotSuccessful_NullEmail(){
        Author author = new Author("ahmed",null,"19-5-1998");
        assertThrows(IllegalArgumentException.class , () -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(0)).save(author);
    }

    @Test
    void addAuthor_NotSuccessful_NullName(){
        Author author = new Author(null,"ahmed@test.com","19-5-1998");
        assertThrows(IllegalArgumentException.class , () -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(0)).save(author);
    }

    @Test
    void addAuthor_NotSuccessful_NullBirthDate(){
        Author author = new Author("ahmed","ahmed@test.com",null);
        assertThrows(IllegalArgumentException.class , () -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(0)).save(author);
    }

    @Test
    void addAuthor_NotSuccessful_WrongEmailFormat(){
        Author author = new Author("ahmed","ahmedtest",null);
        assertThrows(IllegalArgumentException.class , () -> authorService.addAuthor(author));
        verify(jpaAuthorRepository, times(0)).save(author);
    }

    @Test
    void getAuthorByIdDTO_Successful() {
        Author author = new Author();
        author.setAuthor_name("name");
        AuthorDTO authorDTO = new AuthorDTO();
        when(jpaAuthorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorMapper.authorToAuthorDTO(author)).thenReturn(authorDTO);

        AuthorDTO result = authorService.getAuthorByIdDto(1);
        System.out.println(result + " " +author);
        assertEquals(authorDTO, result);

    }

    @Test
    void getAuthorByIdDTO_NotSuccessful() {
        when(jpaAuthorRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class , () -> authorService.getAuthorByIdDto(1));
    }

}