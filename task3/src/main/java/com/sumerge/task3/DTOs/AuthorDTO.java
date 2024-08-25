package com.sumerge.task3.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sumerge.task3.DatabaseClasses.Course;

import java.util.HashSet;
import java.util.Set;

public class AuthorDTO {
    private int author_id;
    private String author_name;
    private String author_email;
    private String author_birthdate;
    @JsonIgnoreProperties({"authors", "assessment", "ratings"})
    private Set<Course> courses  = new HashSet<>();

    public AuthorDTO() {}

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
