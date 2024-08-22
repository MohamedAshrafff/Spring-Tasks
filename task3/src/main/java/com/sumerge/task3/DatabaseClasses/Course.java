package com.sumerge.task3.DatabaseClasses;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    private String course_name;
    private String course_description;
    private int course_credit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assessment_id", referencedColumnName = "assessment_id")
    private Assessment assessment = new Assessment();

    @ManyToMany
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<Author>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<Rating>();

    public Course(){}

    public Course(String course_name){
        this.course_name = course_name;
    }

    public Course(String course_name, String course_description, int course_credit) {
        this.course_name = course_name;
        this.course_description = course_description;
        this.course_credit = course_credit;
    }


    public Course(String course_name, int course_id, int course_credit, String course_description) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.course_credit = course_credit;
        this.course_description = course_description;
    }


    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }



    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }


    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(int course_credit) {
        this.course_credit = course_credit;
    }

    public String getCourse_description() {
        return course_description;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }
    public void addAuthor(Author author) {
        authors.add(author);
    }
}
