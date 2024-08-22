package com.sumerge.task3.DatabaseClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;
    private String author_name;
    @Column(unique = true)
    private String author_email;
    private String author_birthdate;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Course> courses  = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


    public Author(){}

    public Author(String author_name, String author_email, String author_birthdate) {
        this.author_name = author_name;
        this.author_email = author_email;
        this.author_birthdate = author_birthdate;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }


    public String getAuthor_birthdate() {
        return author_birthdate;
    }

    public void setAuthor_birthdate(String author_birthdate) {
        this.author_birthdate = author_birthdate;
    }
}
