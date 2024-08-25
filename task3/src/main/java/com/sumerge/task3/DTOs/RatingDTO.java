package com.sumerge.task3.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sumerge.task3.DatabaseClasses.Course;

public class RatingDTO {
    private int rating_id;
    private int rating_number;
    @JsonIgnoreProperties({"course_credit" , "assessment" , "ratings" , "authors"})
    private Course course;

    public RatingDTO(){}

    public RatingDTO(int rating_id, int rating_number, Course course) {
        this.rating_id = rating_id;
        this.rating_number = rating_number;
        this.course = course;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public int getRating_number() {
        return rating_number;
    }

    public void setRating_number(int rating_number) {
        this.rating_number = rating_number;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
