package com.sumerge.task3.DatabaseClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    private String course_name;
    private String course_description;
    private int course_credit;
    private int author_id;

    public Course(){}

    public Course(String course_name){
        this.course_name = course_name;
    }

    public Course(String course_name, String course_description, int author_course_id, int course_credit) {
        this.course_name = course_name;
        this.course_description = course_description;
        this.course_credit = course_credit;
        this.author_id = author_course_id;
    }

    public Course(String course_name, int course_id, int author_course_id, int course_credit, String course_description) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.author_id = author_course_id;
        this.course_credit = course_credit;
        this.course_description = course_description;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
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

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
