package com.sumerge.task3.DatabaseClasses;

import javax.persistence.*;

@Entity
@Table(name = "assessment")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assessment_id;
    private String assessment_content;

    @OneToOne(mappedBy = "assessment")
    private Course course;

    public Assessment() {}

    public Assessment(String assessment_content, Course course) {
        this.assessment_content = assessment_content;
        this.course = course;
    }

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }

    public String getAssessment_content() {
        return assessment_content;
    }

    public void setAssessment_content(String assessment_content) {
        this.assessment_content = assessment_content;
    }
}
