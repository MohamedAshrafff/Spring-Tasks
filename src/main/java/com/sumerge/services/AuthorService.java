package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.AuthorMapper;
import com.sumerge.repos.JPAAuthorRepository;
import com.sumerge.task3.DTOs.AuthorDTO;
import com.sumerge.task3.DatabaseClasses.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    JPAAuthorRepository jpaAuthorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public AuthorDTO getAuthorByEmail(String email) {
        Author retrievedAuthor = jpaAuthorRepository.findAuthorByEmail(email).orElse(null);
        if(retrievedAuthor == null){
            throw new NotFoundException("Author with email "+email+" not found");
        }
        return authorMapper.authorToAuthorDTO(retrievedAuthor);
    }
    public Author getAuthorById(int id) {
        Author retrievedAuthor = jpaAuthorRepository.findById(id).orElse(null);
        if(retrievedAuthor == null){
            throw new NotFoundException("Author with id "+id+" not found");
        }
        return jpaAuthorRepository.findById(id).orElse(null);
    }

    public AuthorDTO getAuthorByIdDto(int id) {
        Author retrievedAuthor = jpaAuthorRepository.findById(id).orElse(null);
        if(retrievedAuthor == null){
            throw new NotFoundException("Author with id "+id+" not found");
        }
        return authorMapper.authorToAuthorDTO(retrievedAuthor);
    }

    public AuthorDTO addAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        if (author.getAuthor_email() == null || author.getAuthor_email().trim().isEmpty()) {
            throw new IllegalArgumentException("Author email cannot be null or empty.");
        }

        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!author.getAuthor_email().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        if (author.getAuthor_name() == null || author.getAuthor_name().trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }

        if (author.getAuthor_birthdate() == null || author.getAuthor_birthdate().trim().isEmpty()) {
            throw new IllegalArgumentException("Author birthdate cannot be null or empty.");
        }

        return authorMapper.authorToAuthorDTO(jpaAuthorRepository.save(author));
    }
}
