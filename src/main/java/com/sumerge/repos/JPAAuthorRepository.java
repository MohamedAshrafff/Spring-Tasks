package com.sumerge.repos;

import com.sumerge.task3.DatabaseClasses.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAAuthorRepository extends JpaRepository <Author , Integer>{

    @Query(value = "select a from Author a where a.author_email = ?1")
    Optional<Author> findAuthorByEmail(String email);

}
