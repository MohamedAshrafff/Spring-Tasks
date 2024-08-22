package com.sumerge.task3.DatabaseClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rating" )
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rating_id;
    private int rating_number;


    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;


    public Rating() {}

    public Rating(int rating_id, int rating_number, Course course) {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating_number() {
        return rating_number;
    }

    public void setRating_number(int rating_number) {
        this.rating_number = rating_number;
    }
}
