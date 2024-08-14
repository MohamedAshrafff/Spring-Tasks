package com.sumerge.task3.DatabaseClasses;

public class Course {
    private String courseName;
    private String courseDescription;
    private int courseCredit;
    private int author_id;

    public Course(String courseName, String courseDescription, int author_id, int courseCredit) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.author_id = author_id;
        this.courseCredit = courseCredit;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

}
