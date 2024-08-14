package com.sumerge.task3.DatabaseClasses;

public class Author {
    private int authorId;
    private String authorName;
    private String authorEmail;
    private String authorDate;
    private int course_id;

    public Author(int authorId, int course_id, String authorDate, String authorEmail, String authorName) {
        this.authorId = authorId;
        this.course_id = course_id;
        this.authorDate = authorDate;
        this.authorEmail = authorEmail;
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorDate() {
        return authorDate;
    }

    public void setAuthorDate(String authorDate) {
        this.authorDate = authorDate;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
