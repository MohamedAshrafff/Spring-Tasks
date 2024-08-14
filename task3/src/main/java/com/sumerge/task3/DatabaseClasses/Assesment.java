package com.sumerge.task3.DatabaseClasses;

public class Assesment {
    private int assessmentId;
    private String assessmentContent;
    private int courseId;

    public Assesment(int assessmentId, String assessmentContent, int courseId) {
        this.assessmentId = assessmentId;
        this.assessmentContent = assessmentContent;
        this.courseId = courseId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
