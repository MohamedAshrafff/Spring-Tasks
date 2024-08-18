package com.sumerge.services;

import com.sumerge.repos.JPAAuthorRepository;
import com.sumerge.task3.DatabaseClasses.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    JPAAuthorRepository jpaAuthorRepository;

    public Author getAuthorByEmail(String email) {
        return jpaAuthorRepository.findAuthorByEmail(email).orElse(null);
    }
    public Author getAuthorById(int id) {
        return jpaAuthorRepository.findById(id).orElse(null);
    }
}
