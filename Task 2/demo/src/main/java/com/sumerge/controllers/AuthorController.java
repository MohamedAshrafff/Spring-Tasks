package com.sumerge.controllers;

import com.sumerge.services.AuthorService;
import com.sumerge.task3.DatabaseClasses.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/email")
    public ResponseEntity<Author> getByEmail(@RequestBody String email) {
        System.out.println(email);
        return ResponseEntity.ok(authorService.getAuthorByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> viewCourseById(@PathVariable int id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
}
