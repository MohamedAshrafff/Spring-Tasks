package com.sumerge.task3.DatabaseClasses;

public class CourseDTO {
    private int course_id;
    private String course_name;
    private String course_description;
    private String assessment_id;
    public CourseDTO() {}

    public CourseDTO(String course_name, int course_id, String course_description , String assessment_id) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.course_description = course_description;
        this.assessment_id = assessment_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "course_name='" + course_name + '\'' +
                ", course_id='" + course_id + '\'' +
                ", course_description='" + course_description + '\'' +
                '}';
    }
}
