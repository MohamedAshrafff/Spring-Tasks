package com.sumerge.controllers;

import com.sumerge.services.AuthorService;
import com.sumerge.task3.DTOs.AuthorDTO;
import com.sumerge.task3.DatabaseClasses.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/email")
    public ResponseEntity<AuthorDTO> getAuthorByEmail(@RequestBody String email) {
        return ResponseEntity.ok(authorService.getAuthorByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> viewAuthorById(@PathVariable int id) {
        return ResponseEntity.ok(authorService.getAuthorByIdDto(id));
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.addAuthor(author));
    }
}
