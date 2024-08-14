package com.sumerge.task3.DatabaseClasses;

public class Rating {
    private int ratingId;
    private int ratingNumber;
    private int courseId;

    public Rating(int ratingId, int ratingNumber, int courseId) {
        this.ratingId = ratingId;
        this.ratingNumber = ratingNumber;
        this.courseId = courseId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
