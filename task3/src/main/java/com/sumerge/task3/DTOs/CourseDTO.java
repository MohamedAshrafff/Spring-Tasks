package com.sumerge.task3.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DatabaseClasses.Author;
import com.sumerge.task3.DatabaseClasses.Rating;

import java.util.Set;

public class CourseDTO {
    private int course_id;

    private String course_name;
    private String course_description;
    private int course_credit;

    @JsonIgnoreProperties("courses")
    private Set<Author> authors;
    @JsonIgnoreProperties("course")
    private Assessment assessment ;
    @JsonIgnoreProperties("course")
    private Set<Rating> ratings ;

    public CourseDTO() {}

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(int course_credit) {
        this.course_credit = course_credit;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
