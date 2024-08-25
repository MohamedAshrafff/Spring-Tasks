package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Author;
import com.sumerge.task3.DTOs.AuthorDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Test
    void authorToAuthorDTO() {
        Author author = new Author();
        author.setAuthor_id(1);
        author.setAuthor_name("Jane Doe");
        author.setAuthor_email("jane.doe@example.com");
        author.setAuthor_birthdate("1990-01-01");

        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(author);

        assertNotNull(authorDTO);
        assertEquals(1, authorDTO.getAuthor_id());
        assertEquals("Jane Doe", authorDTO.getAuthor_name());
        assertEquals("jane.doe@example.com", authorDTO.getAuthor_email());
        assertEquals("1990-01-01", authorDTO.getAuthor_birthdate());
    }

    @Test
    void authorToAuthorDTO_NullAuthor_ShouldReturnNull() {
        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(null);

        assertNull(authorDTO);
    }
}
